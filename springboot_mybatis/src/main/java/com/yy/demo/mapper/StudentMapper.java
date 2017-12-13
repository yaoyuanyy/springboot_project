package com.yy.demo.mapper;

import com.yy.demo.bean.Student;
import com.yy.demo.bean.User;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {

    Student findById(@Param("id") long id);

	int updateSchoolName(@Param("schoolName") String schoolName, @Param("id") long id);

}
