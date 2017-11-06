package com.yy.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Resource
    private WebFilter webFilter;

	/**
	 *<ul>
	 *<li> 自定义static content load path e.g. html jsp freemarker
	 *<li> so you can visit classpath:/mystatic/*的静态文件
	 *<li> e.g.:you can visit url:http://localhost:8080/welcome.html 
	 *</ul> 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("--- config start ---");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/mystatic/").addResourceLocations("classpath:/static/");
	}

	@Bean
	public FilterRegistrationBean webFilterRegistration(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(webFilter);
        bean.addUrlPatterns("/*");
        bean.setName("webFilter");
        bean.setOrder(Integer.MAX_VALUE);
	    return bean;
    }

}
