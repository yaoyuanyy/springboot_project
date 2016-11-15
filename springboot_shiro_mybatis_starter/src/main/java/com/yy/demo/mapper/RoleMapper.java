package com.yy.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yy.demo.bean.Role;

@Mapper
public interface RoleMapper {

	@Insert("insert into role(role_name,role_permission,user_id) values(#{roleName},#{rolePermission},#{userId})")
	public int insert(Role role);
	
	@Select("select role_name roleName,role_permission rolePermission,user_id userId from role where id=#{id}")
	public Role findById(@Param("id") long id);
	//写在**mapper.xml中了
	public Role findByUserId(@Param("userId") long userId);
	

}
