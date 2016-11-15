package com.yy.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.demo.bean.User;
import com.yy.demo.mapper.UserMapper;
import com.yy.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public User fingById(long id) {
		return userMapper.findById(id);
	}

}
