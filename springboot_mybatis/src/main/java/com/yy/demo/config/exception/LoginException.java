package com.yy.demo.config.exception;

/**
 * Description:
 * <pre>
*       登录相关异常
 * </pre>
 * NB.
 * Created by skyler on 2018/1/15 at 下午9:16
 */
public class LoginException extends Exception{

    private int code;

    public LoginException() {}

    public LoginException(String message) {
        this(message, -1);
    }
    public LoginException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
