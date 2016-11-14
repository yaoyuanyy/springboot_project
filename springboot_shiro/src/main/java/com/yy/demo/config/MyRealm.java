package com.yy.demo.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Object principal = principals.getPrimaryPrincipal();
		if(principal.equals("test")) {
			info.addRole("test");
		}
		info.addRole("test2");
		// TODO Auto-generated method stub
		return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO 真实项目中情景：从数据库拿到用户的name和password
		String principal = "test";
		String credentials = "123456";
		
		AuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(), credentials, this.getName());
		
		return info;
	}

}
