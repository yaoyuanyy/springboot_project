package com.yy.demo.service;

import com.yy.demo.bean.User;

import java.io.FileNotFoundException;

public interface IUserService {

	int insert(User user);

	User findById(long id);

    int updateScore(long score, long id);

    User fingByStudentId(long studentId);

    void updateSchoolName(String schoolName, long studentId) throws FileNotFoundException;
}
