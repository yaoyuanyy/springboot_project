package com.okcoin.exchange.c2c.common.enums;


/**
 * 资金划转状态
 * Created by jiangchuan.deng on 2017/10/19.
 */
public enum TransferStatusEnum {
    /**
     * 等待处理
     */
    NOT_YET(0),
    /**
     * 成功
     */
    SUCCESS(1);

    private int code;

    TransferStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
