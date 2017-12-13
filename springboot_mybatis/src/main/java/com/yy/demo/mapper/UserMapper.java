package com.yy.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.yy.demo.bean.User;

public interface UserMapper {

	int insert(User user);

	User findById(@Param("id") long id);

	int updateScore(@Param("score") long score, @Param("id") long id);

    User fingByStudentId(@Param("studentId") long studentId);
}
