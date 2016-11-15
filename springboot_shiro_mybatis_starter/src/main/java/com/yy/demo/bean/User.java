package com.yy.demo.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name="";
	private String mobile;
	private String salt;
	private String password;
	@JsonIgnore
	@JsonFormat(pattern = "yyyyMMdd")
	private Date ctime;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mobile=" + mobile + ", salt=" + salt + ", password=" + password
				+ ", ctime=" + ctime + "]";
	}

}
