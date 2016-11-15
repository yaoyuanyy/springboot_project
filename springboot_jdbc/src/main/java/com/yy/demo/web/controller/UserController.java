package com.yy.demo.web.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yy.demo.bean.User;
import com.yy.demo.service.IUserService;
import com.yy.vo.UserVo;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;

	@RequestMapping("/home")
	public String home() {
		System.out.println("ddd");
		return "Hello World!";
	}

	@RequestMapping("/insert")
	public String insert(User vo) {
		int id = userService.insert(vo);
		System.out.println("id" + id);
		return "success";
	}

	@RequestMapping("/findById")
	public String findById(long id) {
		User user = userService.fingById(id);
		System.out.println("user" + user);
		return "success";
	}
}