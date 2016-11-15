package com.yy.demo.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@MapperScan("com.yy.demo.mapper")
public class MybatisConfig {

    @Resource	
    DataSource dataSource;
	/*@Resource
	MyDataSourceProperties pros;
	 
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(pros.getUsername());
		dataSource.setUrl("spring.datasource.url");
	    dataSource.setUsername("spring.datasource.username");
	    dataSource.setPassword("spring.datasource.password");
	    return dataSource;
	  }*/

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
    	System.out.println("sqlSessionFactoryBean");
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.yy.demo.bean");
        //bean.setTypeAliasesPackage("com.yy.demo.bean");
       // bean.setMapperLocations(new ClassPathResource[] {new ClassPathResource("mapper/*.xml")});

        //分页插件
       /* PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});*/

        //添加XML目录
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
       
        bean.setMapperLocations(resolver.getResources("classpath*:com/yy/**/mapper/*.xml"));
      
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    	System.out.println("sqlSessionTemplate");
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager() {
    	System.out.println("transactionManager");
        return new DataSourceTransactionManager(dataSource);
    }

    /*//implements TransactionManagementConfigurer
    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);

    }*/
}
