package com.yy.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yy.demo.bean.UserAccountTransferRecord;
import com.yy.demo.enums.TransferStatusEnum;
import com.yy.demo.mapper.AccountTransferMapper;
import com.yy.demo.service.AccountTransferService;
import com.yy.demo.service.UserAccountService;
import com.yy.demo.util.HttpUtils;
import com.yy.demo.util.SignUtil;
import com.yy.demo.vo.ResponseResult;
import com.yy.demo.web.config.PubPriKeyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/27 at 上午10:54
 */
@Service
@Slf4j
public class AccountTransferServiceImp implements AccountTransferService {

    @Resource
    private AccountTransferMapper accountTransferMapper;

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private PubPriKeyConfig pubPriKeyConfig;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void transferIn(Long userId, String transferUUid, BigDecimal amount) {
        // 防重转账
        if (accountTransferMapper.queryByTransferUUid(transferUUid) != null) {
            return;
        }

        UserAccountTransferRecord transferRecord = UserAccountTransferRecord.builder()
                .userId(userId)
                .amount(amount)
                .transferUUid(UUID.randomUUID().toString())
                .status(TransferStatusEnum.OK.getCode())
                .build();
        /**
         * NB. (1)必须要在(2)前执行，防止重复提交请求造成重复添加数据，
         * 依赖的是transferRecord表的transferUUid字段的唯一索引功能
         * 但是问题来了，如果这两句代码错位重排了怎么办
         */
        // Step (1)
        accountTransferMapper.insert(transferRecord);

        // Step (2)
        userAccountService.incrBalance(userId, amount);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public UserAccountTransferRecord transferOut(Long userId, String transferUUid, BigDecimal amount) {
        /**
         * 为了防止减重复，这里一定要加锁 sql for update 或 乐观锁。原则是先查后减
         *
         */
        userAccountService.decrBalance(userId, amount);

        UserAccountTransferRecord transferRecord = UserAccountTransferRecord.builder()
                .userId(userId)
                .amount(amount)
                .transferUUid(UUID.randomUUID().toString().replaceAll("-", ""))
                .status(TransferStatusEnum.HANDLING.getCode())
                .retryTimes(0)
                .build();
        accountTransferMapper.insert(transferRecord);

        return transferRecord;
    }

    @Override
    public List<UserAccountTransferRecord> listNotCompleteAssetsTransfer() {
        return null;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void synDataToOtherService(Long accountTransferId) throws IOException {
        UserAccountTransferRecord tr = accountTransferMapper.getByIdForUpdate(accountTransferId);
        if (tr.getStatus() == TransferStatusEnum.HANDLING.getCode()) {
            final Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userId", tr.getUserId());
            paramMap.put("amount", tr.getAmount());
            paramMap.put("transferUUid", tr.getTransferUUid());
            paramMap.put("sign", SignUtil.sign(paramMap, pubPriKeyConfig.getPrivateKey()));

            final HttpUtils.Response response = HttpUtils.post(pubPriKeyConfig.getInUrl(), paramMap);
            log.info("response code : {}, body : {}", response.getCode(), response.getBody());
            final ResponseResult<String> responseResult = JSON.parseObject(response.getBody(),
                    new TypeReference<ResponseResult<String>>() {});
            if (responseResult.getCode() == 0) {
                tr.setStatus(TransferStatusEnum.OK.getCode());
                accountTransferMapper.updateById(tr);
                log.info("syn ok transferUUid: {}", tr.getTransferUUid());
            } else {
                tr.setRetryTimes(tr.getRetryTimes() == null ? 1 : tr.getRetryTimes() + 1);
                accountTransferMapper.updateById(tr);
                log.error("syn fail transferUUid: {}", tr.getTransferUUid());
            }
        }
    }
}
