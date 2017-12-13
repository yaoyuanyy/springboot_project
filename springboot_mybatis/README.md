
# springboot_mybatis
```
this is a simple web project demo with RESTFUL style which contains spring boot,mybatis.
configuration of the project consist of java-config style.
inside spring boot integrates mybatis via spring-boot-starter-jdbc and mybatis-spring;
在下一个demo中，将使用mybatis-springboot-starter集成springboot
```

## version
- spring boot:1.5.4
- mybatis 3.2.5


## test 两个线程同时访问一个带有@Transactional的service方法的情况
- 实验代码：com.yy.demo.service.impl.UserServiceImpl.updateSchoolName

#### 情况1

- 没有@Transactional且user表的studentId字段没有建索引
```
操作：

打印信息：
----
com.yy.demo.mapper.UserMapper.fingByStudentId:select * from user where studentId=2 for update

慢查询执行时间:334ms
studentId:2 schoolName:c
com.yy.demo.mapper.UserMapper.fingByStudentId:select * from user where studentId=2 for update
----

studentId:2 schoolName:ccc
执行时间:2ms
sleep xing le
com.yy.demo.mapper.StudentMapper.updateSchoolName:UPDATE student SET schoolName = 'c' where id=2

执行时间:6ms
sleep xing le
com.yy.demo.mapper.StudentMapper.updateSchoolName:UPDATE student SET schoolName = 'ccc' where id=2

执行时间:5ms

结果：
可以看出，两个线程都在sleep那暂停了，之后修改数据，数据表中的数据最后时schoolName=aaa
```

#### 情况2

- 有@Transactional且user表的studentId字段没有建索引
```
操作：
updateSchoolName加@Transactional

打印信息：
同情况一

结果：
同情况一
```

#### 情况3

- 有@Transactional且user表的studentId字段建索引
```
操作：
user表对studentId字段建立索引

打印信息：
同情况一

结果：
同情况一
```

#### 情况4

- 有@Transactional且user表的studentId字段建索引且select语句加for update
```
操作：
UserMapper.xml中的fingByStudentId加for update

如：select * from user where studentId=2 for update

打印信息：
----
studentId:2 schoolName:c
com.yy.demo.mapper.UserMapper.fingByStudentId:select * from user where studentId=2 for update
执行时间:17ms
----
sleep xing le
com.yy.demo.mapper.StudentMapper.updateSchoolName:UPDATE student SET schoolName = 'c' where id=2

执行时间:2ms
com.yy.demo.mapper.UserMapper.fingByStudentId:select * from user where studentId=2 for update

慢查询执行时间:8422ms
studentId:2 schoolName:ccc
com.yy.demo.mapper.StudentMapper.updateSchoolName:UPDATE student SET schoolName = 'ccc' where id=2
sleep xing le

执行时间:2ms
结果：
从打印信息可以看出，先进来的线程执行完后，第二个线程才能执行select，并且可以从打印的'----'看到，第二个线程堵塞在
User user = userMapper.fingByStudentId(studentId);这条语句上

```

#### 情况5

- 没有@Transactional且user表的studentId字段建索引且select语句加for update。如：select * from user where studentId=2 for update
```
操作：
注释掉updateSchoolName上的@Transactional

打印信息：
同情况一

结果：
同情况一
```

#### 结论
```
加了for update的select语句需要放在数据库的事务中才起作用
```