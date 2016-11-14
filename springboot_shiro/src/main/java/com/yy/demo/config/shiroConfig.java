package com.yy.demo.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class shiroConfig {

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		
		//proxy.setTargetFilterLifecycle(true);
		
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
		return filterRegistration;
	}
	
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager());
		//bean.setLoginUrl("login.html");
		//bean.setSuccessUrl("sample/index");
		//bean.setUnauthorizedUrl("sample/unauthor");
		
		Map<String,Filter> filters = new HashMap<>();
		filters.put("roles", new RolesAuthorizationFilter());
		filters.put("user", new UserFilter());
		filters.put("userAthe", userAtheFilter());
		filters.put("authc", new FormAuthenticationFilter());
		bean.setFilters(filters);
		
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/unauthor", "anon");
		filterChainDefinitionMap.put("/logout", "anon");
		//filterChainDefinitionMap.put("/login.html", "anon");
		filterChainDefinitionMap.put("/user/*", "userAthe");
		filterChainDefinitionMap.put("/student/*", "user,roles[test]");
		filterChainDefinitionMap.put("/**", "anon");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
		
	}

	@Bean("securityManager")
	public SecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(myRealm());
		manager.setSessionManager(defaultWebSessionManager());
		//SecurityUtils.setSecurityManager(manager);//桌面程序(无web环境)需要调用这个方法
		return manager;
	}
	
	public CacheManager cacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return cacheManager;
		
	}
	@Bean(name="sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheManager());
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }
	
	@Bean
	public MyRealm myRealm(){
		MyRealm realm = new MyRealm();
		return realm;
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean
	public UserAtheFilter userAtheFilter(){
		return new UserAtheFilter();
	}
	
}
