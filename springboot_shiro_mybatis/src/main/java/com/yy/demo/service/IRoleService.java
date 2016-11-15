package com.yy.demo.service;

import com.yy.demo.bean.Role;

public interface IRoleService {

	public int insert(Role role);

	public Role fingById(long id);

	public Role findByUserId(long userId);
}
