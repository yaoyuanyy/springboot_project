package com.yy.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yy.demo.bean.Role;
import com.yy.demo.mapper.RoleMapper;
import com.yy.demo.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public int insert(Role role) {
		return roleMapper.insert(role);
	}

	@Override
	public Role fingById(long id) {
		return roleMapper.findById(id);
	}

	@Override
	public Role findByUserId(long userId) {
		return roleMapper.findByUserId(userId);
	}

}
