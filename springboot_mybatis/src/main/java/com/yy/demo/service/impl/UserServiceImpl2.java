package com.yy.demo.service.impl;

import javax.annotation.Resource;

import com.yy.demo.mapper.StudentMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.yy.demo.bean.User;
import com.yy.demo.mapper.UserMapper;
import com.yy.demo.service.IUserService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class UserServiceImpl implements IUserService {

    private static int iii=0;
//
//    @Resource
//    private DataSourceTransactionManager manager;

	@Resource
	private UserMapper userMapper;

    @Resource
    private StudentMapper studentMapper;

	@Override
    @Transactional(rollbackFor = Throwable.class)
	public int insert(User user) {
        int count = userMapper.insert(user);
        // 验证事务是否生效
        //int i = 10/0;
		return count;
	}

	@Override
	public User findById(long id) {
		return userMapper.findById(id);
	}

    private User PrivateFindById(long id) {
        return userMapper.findById(id);
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int updateScore(long score, long id) {

//        String name = TransactionSynchronizationManager.getCurrentTransactionName();
//        System.out.println("--- TransactionName:"+name);

        int count = userMapper.updateScore(iii, id);
        try {
            Thread.sleep(1000*20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public User fingByStudentId(long studentId) {
        User user = userMapper.fingByStudentId(studentId);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateSchoolName(String schoolName, long studentId) {
        System.out.println("----");
        User user = userMapper.fingByStudentId(studentId);
        System.out.println("studentId:"+studentId+" schoolName:"+schoolName);
        updateScore(11L,11);

        studentMapper.updateSchoolName(schoolName, user.getStudentId());
    }

    @Transactional(rollbackFor = Throwable.class)
    protected void privateUpdateSchoolName(String schoolName, long studentId) {
        System.out.println("----");
        User user = userMapper.fingByStudentId(studentId);
        System.out.println("studentId:"+studentId+" schoolName:"+schoolName);

        studentMapper.updateSchoolName(schoolName, user.getStudentId());
    }


}
