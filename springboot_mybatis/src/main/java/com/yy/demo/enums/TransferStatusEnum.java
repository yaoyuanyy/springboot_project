package com.yy.demo.enums;


/**
 * Description:
 * <p></p>
 * <pre>
 *     UserAccountTransferRecord转账状态
 * </pre>
 * NB.
 * Created by skyler on 2018/5/27 at 上午10:54
 */
public enum TransferStatusEnum {
    /**
     * 处理中
     */
    HANDLING(0),
    /**
     * 成功
     */
    OK(1);

    private int code;

    TransferStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
