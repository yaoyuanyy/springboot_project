package com.yy.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/hello")
    public String hello() {
    	
    	//SecurityManager manager = SecurityUtils.getSecurityManager();
        return "Hello World!";
    }
   
}