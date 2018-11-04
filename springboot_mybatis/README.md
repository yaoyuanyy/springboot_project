
# springboot_mybatis
```
this is a simple web project demo with RESTFUL style which contains spring boot,mybatis.
configuration of the project consist of java-config style.
inside spring boot integrates mybatis via spring-boot-starter-jdbc and mybatis-spring;
在下一个demo中，将使用mybatis-springboot-starter集成springboot
```

## version
- spring boot:1.5.10
- mybatis 3.4.6
- mybatis-spring-boot-starter 1.3.2
- druid-spring-boot-starter 1.1.10


## launch the project

```
$ mvn clean install

profile=env/test
$ java -jar projectName.jar --spring.profiles.active=profile

$ curl http://localhost:8000/user/findById?id=3

you can get as follow

{"code":0,"msg":"success","data":{"id":3,"studentId":3,"name":"s","mobile":"111","score":0}}


```



## test1
两个线程同时访问一个带有@Transactional的service方法的情况
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

## test2 2018-1-2
利用自定义注解、拦截器HandlerInterceptorAdapter实现登录验证；利用自定义注解和HandlerMethodArgumentResolver实现




## 集成swagger自动生成文档 2018-2-8
```
1. 
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.7.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.7.0</version>
</dependency>

2.
AppConfig.addResourceHandlers(ResourceHandlerRegistry registry)方法添加：
registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
                
否则访问：http://localhost:8000/swagger-ui.html，报404错误

3.
启动项目，访问：http://localhost:8000/swagger-ui.html，查看页面结果
此时会生成粗略的文档，要想生成细颗粒度的文档，具体参见代码controller类控制

```