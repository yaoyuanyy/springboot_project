package com.yy.demo.config;

import com.yy.demo.web.anno.AttrbuteArgResolver;
import com.yy.demo.web.anno.LoginHandlerInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//	    List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>();
//	    resolvers.add(attrbuteArgResolver());
	    //super.addArgumentResolvers(resolvers);
        argumentResolvers.add(new AttrbuteArgResolver());
    }

    @Bean
    public AttrbuteArgResolver attrbuteArgResolver(){
	    return new AttrbuteArgResolver();
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        super.addReturnValueHandlers(returnValueHandlers);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor());
        //registry.addWebRequestInterceptor();
    }
}
