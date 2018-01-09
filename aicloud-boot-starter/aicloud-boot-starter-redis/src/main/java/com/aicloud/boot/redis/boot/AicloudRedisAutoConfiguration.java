package com.aicloud.boot.redis.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import com.aicloud.boot.redis.AicloudRedisObjectSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableConfigurationProperties(AicloudRedisProperties.class)
public class AicloudRedisAutoConfiguration {
	@Autowired
	private AicloudRedisProperties aicloudRedisProperties;
	
	/**
	 * @ConfigurationProperties注入了对Redis的密码配置(默认是null),不信的话，大家可以看下源码JedisConnectionFactory
	 * JedisConnectionFactory里面封装了JedisShardInfo
	 * 有必要的话，可以自定义配置JedisShardInfo、poolConfig
	*/
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(aicloudRedisProperties.getPoolmaxactive());
		poolConfig.setMaxIdle(aicloudRedisProperties.getPoolmaxidle());
		poolConfig.setMinIdle(aicloudRedisProperties.getPoolminidle());
		poolConfig.setMaxWaitMillis(aicloudRedisProperties.getPoolmaxwait());
        return poolConfig;
    }
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setPoolConfig(jedisPoolConfig());
		factory.setHostName(aicloudRedisProperties.getHost());
		if(!StringUtils.isEmpty(aicloudRedisProperties.getPassword())){
			factory.setPassword(aicloudRedisProperties.getPassword());
		}
		factory.setPort(aicloudRedisProperties.getPort());
		factory.setTimeout(aicloudRedisProperties.getTimeout());
        return factory;
    }
	//泛型RedisTemplate
    @Bean
    public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, T> template = new RedisTemplate<String, T>();
        //从jedisConnectionFactory获取Jedis实例
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        //序列化与反序列化
        template.setValueSerializer(new AicloudRedisObjectSerializer());
        return template;
    }
}
