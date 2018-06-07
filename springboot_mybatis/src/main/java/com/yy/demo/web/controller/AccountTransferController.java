package com.yy.demo.web.controller;

import com.yy.demo.bean.UserAccountTransferRecord;
import com.yy.demo.service.UserAccountTransferService;
import com.yy.demo.util.SignUtil;
import com.yy.demo.vo.ResponseResult;
import com.yy.demo.web.config.PubPriKeyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/27 at 上午8:11
 */
@RestController
@RequestMapping("/v1/accountTransfer")
@Slf4j
@EnableConfigurationProperties(PubPriKeyConfig.class)
public class AccountTransferController {

    @Resource
    private UserAccountTransferService userAccountTransferService;

    @Resource
    private PubPriKeyConfig pubPriKeyConfig;

    /**
     * <pre>
     *     参数例子：
     *     curl -d "userId=1111&&transferUUid=3d9f78ac-5f8d-4af0-9bc3-842f8316a17c&amount=12.3322222&sign=UeR1Wd9JnLt/pqlz6PWFDawLNOY3uwAGGhOXXUM7TjFW09cMgzNvRjry85MDOk1Tfg2DIZMfwaSs3y4LscoasOYH5cRFWeARFTHfNb/E4NK1IIX9v5ceUk76nTiozMOp3ZCmMA280/B%2BKQgd78s52XQBP0p/fzvL8PaCCuhh7l8=" -X POST http://localhost:8000/v1/accountTransfer/in
     *
     *     注意：sign参数中%2B原是+，但是+会被springmvc转成空格，导致验证不通过，所以需要把+转化
     *
     *     curl命令参考：
     *     https://gist.github.com/subfuzion/08c5d85437d5d4f00e58
     * </pre>
     * @param userId
     * @param transferUUid
     * @param amount
     * @param request 包含sign
     * @return
     */
    @PostMapping(value = "/in")
    public ResponseResult transferIn(Long userId, String transferUUid, BigDecimal amount, final HttpServletRequest request) {
        signVerify(request);
        try {
            this.userAccountTransferService.transferIn(userId, transferUUid, amount);
        } catch (final DuplicateKeyException e) { }
        return ResponseResult.ok();
    }

    @PostMapping(value = "/out")
    public ResponseResult transferOut(Long userId, @RequestParam(required = false) String transferUUid, BigDecimal amount,
                                      final HttpServletRequest request) {
        signVerify(request);
        final UserAccountTransferRecord transferRecord = this.userAccountTransferService.transferOut(userId, transferUUid, amount);
        try {
            // 同步不成功会通过定时任务跑status为处理中转账单，从而达到最终一致性的目的
            this.userAccountTransferService.synDataToOtherService(transferRecord.getId());
        } catch (final Exception e) {
            log.error("同步失败。流水号，transferUUid: " + transferRecord.getTransferUUid(), e);
        }
        return ResponseResult.ok();
    }

    private void signVerify(final HttpServletRequest request) {
        final boolean verifyOK = SignUtil.verify(getParamMap(request), this.pubPriKeyConfig.getPublicKey());
        if (!verifyOK) {
            //throw new skylerException(ErrorCodeEnum.SIGN_ERROR);
            throw new RuntimeException("dd");
        }
    }

    private Map<String, String> getParamMap(final HttpServletRequest request) {
        final Map<String, String> paramMap = new HashMap<>();

        final Enumeration<String> keys = request.getParameterNames();

        while (keys.hasMoreElements()) {
            final String name = keys.nextElement();
            paramMap.put(name, request.getParameter(name));
        }

        return paramMap;
    }

}
