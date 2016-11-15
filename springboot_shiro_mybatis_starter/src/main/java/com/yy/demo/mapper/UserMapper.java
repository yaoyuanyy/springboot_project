package com.yy.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yy.demo.bean.User;

/**
 * 请注意：构建项目框架时,如果需要定义**mapper.xml,请尽量保持**mapper.java与**mapper.xml在相同的包下，例如本例，否则会报错：bindException
 */ 
//@Mapper使用这个注解后不再需要定义**mapper.xml了
public interface UserMapper {

	public int insert(User user);

	public User findById(@Param("id") long id);

	public User fingByMobile(@Param("mobile")String mobile);

}
