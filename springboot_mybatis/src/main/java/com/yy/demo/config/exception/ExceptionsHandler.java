package com.yy.demo.config.exception;

import com.yy.demo.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/1/15 at 下午9:15
 */
@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseResult handlerLogin(LoginException e){
        log.error("LoginException: {}",e.getMessage());
        return ResponseResult.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult handler(Exception e){
        log.error("ExceptionsHandler Exception: {}",e.getMessage());
        if (e instanceof LoginException){

        }
        return ResponseResult.fail(e.getMessage());
    }
}
