package com.yy.demo.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;

public class UserAtheFilter extends UserFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		// TODO Auto-generated method stub
		return super.isAccessAllowed(request, response, mappedValue);
	}

	/**
	 * 在isAccessAllowed()方法返回false的时候触发，默认是调用/login.jsp给页面
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//return super.onAccessDenied(request, response);
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.getWriter().write(JSON.toJSONString("没有登录，请登录"));
		return false;
	}

}
