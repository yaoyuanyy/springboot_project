package com.yy.demo.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @RequestMapping("/home")
    @ResponseBody
    String home() {
    	System.out.println("ddd");
        return "Hello World!";
    }

   
}