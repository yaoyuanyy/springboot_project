package com.yy.demo.service;

import com.yy.demo.bean.User;

public interface IUserService {

	int insert(User user);

	User fingById(long id);

    int updateScore(long score, long id);
}
