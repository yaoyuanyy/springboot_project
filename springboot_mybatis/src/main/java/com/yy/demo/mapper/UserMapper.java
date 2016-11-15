package com.yy.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.yy.demo.bean.User;

public interface UserMapper {

	public int insert(User user);

	public User findById(@Param("id") long id);

}
