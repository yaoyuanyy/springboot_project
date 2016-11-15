package com.yy.demo.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@RequestMapping("hello")
	public String hello() {
		
		Subject subject = SecurityUtils.getSubject();
		System.out.println("hasRole:"+subject.hasRole("test"));
		return "student";
	}
}
