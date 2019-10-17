package com.yy.demo.service.impl;

import com.yy.demo.bean.User;
import com.yy.demo.mapper.StudentMapper;
import com.yy.demo.mapper.UserMapper;
import com.yy.demo.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl2{


	@Resource
	private UserMapper userMapper;

    @Resource
    private StudentMapper studentMapper;

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.NESTED)
    public int updateScore(long score, long id) {

        System.out.println("updateScore");
        return 1;
    }
}
