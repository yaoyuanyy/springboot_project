package com.yy.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.demo.bean.User;
import com.yy.demo.mapper.UserMapper;
import com.yy.demo.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;

	@Override
    @Transactional(rollbackFor = Throwable.class)
	public int insert(User user) {
        int count = userMapper.insert(user);
        // 验证事务是否生效
        //int i = 10/0;
		return count;
	}

	@Override
	public User fingById(long id) {
		return userMapper.findById(id);
	}

}
