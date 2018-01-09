package com.aicloud.boot.redis.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("aicloud.redis")
public class AicloudRedisProperties {
	private String host;
	private String password;
	private int port;
	/**
	*Redis数据库索引（默认为0）
	**/
	private int database=0;
	/**
	*连接池最大连接数（使用负值表示没有限制）
	**/
	private int poolmaxactive = 30;
	/**
	*连接池最大阻塞等待时间（使用负值表示没有限制）毫秒
	**/
	private int poolmaxwait = 10000;
	/**
	*连接池中的最大空闲连接
	**/
	private int poolmaxidle = 5;
	/**
	*连接池中的最小空闲连接
	**/
	private int poolminidle = 5;
	/**
	*超时
	**/
	private int timeout = 5000;
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getPoolmaxactive() {
		return poolmaxactive;
	}
	public void setPoolmaxactive(int poolmaxactive) {
		this.poolmaxactive = poolmaxactive;
	}
	public int getPoolmaxwait() {
		return poolmaxwait;
	}
	public void setPoolmaxwait(int poolmaxwait) {
		this.poolmaxwait = poolmaxwait;
	}
	public int getPoolmaxidle() {
		return poolmaxidle;
	}
	public void setPoolmaxidle(int poolmaxidle) {
		this.poolmaxidle = poolmaxidle;
	}
	public int getPoolminidle() {
		return poolminidle;
	}
	public void setPoolminidle(int poolminidle) {
		this.poolminidle = poolminidle;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDatabase() {
		return database;
	}
	public void setDatabase(int database) {
		this.database = database;
	}

}
