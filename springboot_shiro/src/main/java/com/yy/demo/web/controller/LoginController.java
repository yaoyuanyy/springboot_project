package com.yy.demo.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/sample")
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
    	
    	//SecurityManager manager = SecurityUtils.getSecurityManager();
        return "Hello World!";
    }
    
    @RequestMapping(value="/login")
    public String login(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password, Map<String, Object> model) {
    	UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    	try {
			SecurityUtils.getSubject().login(token);
			System.out.println(response.getContentType()+" ");
			response.getHeaderNames().forEach(item -> System.out.println(item));
			return "index";
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("login fail:{}", e.getMessage());
		}
    	return "e";
    }
    
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
    	try {
			SecurityUtils.getSubject().logout();
			System.out.println(response.getContentType()+" logout ");
			return "logout";
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("login fail:{}", e.getMessage());
		}
    	return "e";
    }
    
    

   
}