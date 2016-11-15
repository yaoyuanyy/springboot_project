# sprinboot_mybatis_starter
 ```
这个项目为springboot集成mybatis的demo例子,通过使用mybatis-spring-boot-starter包进行集成，此包包含了spring-boot-starter-jdbc，mybatis-spring,mybatis,所以再加入mysal驱动包mysql-connector-java就可以
此项目对于mapper层的实现进行了两种方式的演示：
1.在创建**mapper.java上使用注解@mapper
2.在创建**mapper.java的同时，在相同的包下创建**mapper.xml,采用这种方式时，请保持名字相同，包括报名，否则需要显性关联，否则报错: bindException
此外，采用这种方式，需要在mybatisConfig类上加入@MapperScan("xxx"),xxx为**mapper.java所属的包名

```
## version
- spring-boot 1.4.2
- mybatis-spring-boot-starter 1.1.1