package com.yy.demo.config.datasource;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 监控url路径: http://localhost:8000/druid/api.html
 * 参考:
 * https://segmentfault.com/a/1190000015564484
 * https://juejin.im/post/5aceec94f265da2395315d68
 * 字段含义：
 * https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8
 */
@Configuration
@EnableTransactionManagement
//@MapperScan("com.yy.demo.mapper")
public class MybatisConfig {

    protected static final String CONFIG_LOCATION = "classpath:mybatis/mybatis-config.xml";

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String, String> initParams = new HashMap<>();
        //　可配的属性都在 StatViewServlet 和其父类下
        initParams.put("loginUsername", "admin-druid");
        initParams.put("loginPassword", "111111");
        servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidWebStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

//    @Resource
//    DataSource dataSource;


    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }
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
        bean.setDataSource(dataSource());
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
        bean.setConfigLocation(resolver.getResource(CONFIG_LOCATION));
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
        return new DataSourceTransactionManager(dataSource());
    }

    /*//implements TransactionManagementConfigurer
    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);

    }*/
}
