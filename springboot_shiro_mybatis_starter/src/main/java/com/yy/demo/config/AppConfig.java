package com.yy.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

	/**
	 *<ul>
	 *<li> 自定义static content load path e.g. html jsp freemarker
	 *<li> so you can visit classpath:/mystatic/*的静态文件
	 *<li> e.g.:you can visit url:http://localhost:8080/welcome.html 
	 *</ul> 
	 */
	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("--- config start ---");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/mystatic/").addResourceLocations("classpath:/static/");
	}*/
	
	
	@Bean("cacheFilter")
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setFilter(new CacheFilter());
		filter.addUrlPatterns("/*");
		return filter;
	}
	
	/*@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		System.out.println("-InternalResourceViewResolver-");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		//resolver.setViewClass(new InternalResourceView().getClass());
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		return resolver;
	}*/

}
