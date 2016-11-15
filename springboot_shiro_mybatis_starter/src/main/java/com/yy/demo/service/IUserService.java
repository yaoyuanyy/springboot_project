package com.yy.demo.service;

import com.yy.demo.bean.User;

public interface IUserService {

	public int insert(User user);

	public User fingById(long id);

	public User fingByMobile(String mobile);

	public void register(String mobile, String password);
}
