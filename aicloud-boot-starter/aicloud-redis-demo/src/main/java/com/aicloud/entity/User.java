package com.aicloud.entity;

import java.io.Serializable;

public class User implements Serializable{
	/**
	* @author zhoutianqi
	* @className User.java
	* @date 2017年6月5日 上午10:35:44
	* @description 
	*/
	private static final long serialVersionUID = -1L;
	private String username;
	private String password;
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
