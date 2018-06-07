package com.yy.demo.service;
import com.yy.demo.bean.UserAccountTransferRecord;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface UserAccountTransferService {

    /**
     * 转入
     *
     */
    void transferIn(Long userId, String transferUUid, BigDecimal amount);

    /**
     * 转出
     */
    UserAccountTransferRecord transferOut(Long userId, String transferUUid, BigDecimal amount);

    /**
     * 获取未完成的转账单
     *
     * @return
     */
    List<UserAccountTransferRecord> listNotCompleteAssetsTransfer();

    /**
     * 调用其他服务的接口，同步数据
     */
    void synDataToOtherService(Long accountTransferId) throws IOException;

}
