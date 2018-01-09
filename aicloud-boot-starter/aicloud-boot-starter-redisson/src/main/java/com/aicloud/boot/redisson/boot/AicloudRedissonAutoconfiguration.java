package com.aicloud.boot.redisson.boot;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.aicloud.boot.redis.boot.AicloudRedisAutoConfiguration;
import com.aicloud.boot.redis.boot.AicloudRedisProperties;

@Configuration
@AutoConfigureAfter(AicloudRedisAutoConfiguration.class)
public class AicloudRedissonAutoconfiguration {
	/**
	* @author zhoutianqi
	* @className RedissonConf.java
	* @date 2017年6月22日 下午4:29:32
	* @description 1、单机模式
	*				redisson lock
	*				redisson.address=redis://10.18.75.115:6379
	*				redisson.password=
	*				这里如果不加redis://前缀会报URI构建错误，
	*				
	*				Caused by: java.net.URISyntaxException: Illegal character in scheme name at index 0
	*				其次，在redis进行连接的时候如果不对密码进行空判断，会出现AUTH校验失败的情况。
	*				
	*				Caused by: org.redisson.client.RedisException: ERR Client sent AUTH, but no password is set. channel
	*
	*/
	private static final String prefix = "redis://";
	@Autowired
	private AicloudRedisProperties aicloudRedisProperties;
	
	@Bean
	public RedissonClient createRedissonClient(){
		Config config = new Config();
		if(!StringUtils.isEmpty(aicloudRedisProperties.getPassword())){
			config.useSingleServer().setAddress(AicloudRedissonAutoconfiguration.prefix+aicloudRedisProperties.getHost()+":"+String.valueOf(aicloudRedisProperties.getPort())).setPassword(aicloudRedisProperties.getPassword());
		}else{
			config.useSingleServer().setAddress(AicloudRedissonAutoconfiguration.prefix+aicloudRedisProperties.getHost()+":"+String.valueOf(aicloudRedisProperties.getPort()));
		}
		return Redisson.create(config);
	}
}
