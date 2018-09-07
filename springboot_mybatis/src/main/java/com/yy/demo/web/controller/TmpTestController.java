package com.yy.demo.web.controller;

import com.yy.demo.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/9/3 at 下午9:58
 */
@RestController
@RequestMapping("/v1/accountTransfer")
@Slf4j
@Validated
public class TmpTestController {

    @RequestMapping("/save")
    public ResponseResult save(@Valid BeanVo vo){
        System.out.println(vo.getPhone());
        return null;
    }
}
