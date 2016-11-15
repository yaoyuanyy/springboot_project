package com.yy.demo.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.demo.bean.User;
import com.yy.demo.mapper.UserMapper;
import com.yy.demo.service.IUserService;
import com.yy.demo.util.EncryptUtils;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;
	
	@Transactional(rollbackFor=Throwable.class)
	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public User fingById(long id) {
		return userMapper.findById(id);
	}

	@Override
	public User fingByMobile(String mobile) {
		return userMapper.fingByMobile(mobile);
	}

	@Transactional(rollbackFor=Throwable.class)
	@Override
	public void register(String mobile, String password) {
		User user = new User();
		user.setMobile(mobile);
		String salt = EncryptUtils.randomSalt();
		String enPwd = EncryptUtils.encode(password, salt);
		user.setSalt(salt);
		user.setPassword(enPwd);
		user.setCtime(new Date());
		userMapper.insert(user);
	}

}
