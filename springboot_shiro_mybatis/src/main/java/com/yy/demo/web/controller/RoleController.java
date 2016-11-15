package com.yy.demo.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yy.demo.bean.Role;
import com.yy.demo.service.IRoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource
	private IRoleService roleService;

	@RequestMapping("/home")
	public String home() {
		System.out.println("ddd");
		return "Hello World!";
	}

	@RequestMapping("/insert")
	public String insert(Role vo) {
		int id = roleService.insert(vo);
		System.out.println("id" + id);
		return "success";
	}

	@RequestMapping("/findById")
	public String findById(long id) {
		Role role = roleService.fingById(id);
		log.info("user{}", role);
		return JSON.toJSONString(role);
	}
	
	@RequestMapping("/findByUserId")
	public String findByUserId(long userId) {
		//Role role = roleService.fingById(id);
		Role role = roleService.findByUserId(userId);
		log.info("user{}", role);
		return JSON.toJSONString(role);
	}
}