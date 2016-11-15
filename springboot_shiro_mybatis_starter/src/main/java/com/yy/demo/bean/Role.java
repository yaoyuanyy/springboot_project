package com.yy.demo.bean;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String roleName;
	private String rolePermission;
	private long userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRolePermission() {
		return rolePermission;
	}

	public void setRolePermission(String rolePermission) {
		this.rolePermission = rolePermission;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", userId=" + userId + "]";
	}
}
