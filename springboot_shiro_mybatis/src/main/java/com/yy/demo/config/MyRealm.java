package com.yy.demo.config;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yy.demo.bean.User;
import com.yy.demo.service.IUserService;

public class MyRealm extends AuthorizingRealm {

	private static final Logger logger =  LoggerFactory.getLogger(MyRealm.class);
	 
	@Resource
	private IUserService userService;
	
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
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		if(authenticationToken instanceof UsernamePasswordToken) {
			UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
			String mobile = token.getUsername();
			User user = userService.fingByMobile(mobile);
			if(user == null) {
				logger.error("user:{} not exist", mobile);
				throw new AuthenticationException();
			}
			AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt().getBytes()), getName());
			
			return info;
		}
		return null;
	}

}
