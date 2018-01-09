package com.aicloud;

import java.util.List;
import java.util.Set;

import org.redisson.api.RListMultimap;
import org.redisson.api.RSetMultimap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class Application {
	/**
	* @author zhoutianqi
	* @className Application.java
	* @date 2018年1月5日 上午10:30:40
	* @description 
	*/
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private RedissonClient redissonClient;
	@SuppressWarnings("unused")
	@RequestMapping(value = "redisson")
	public String testRedis(){
		RSetMultimap<SimpleKey, String> map = redissonClient.getSetMultimap("myMultimap");
		map.put(new SimpleKey("0"), "1");
		map.put(new SimpleKey("0"), "11");
		map.put(new SimpleKey("0"), "11");
		map.put(new SimpleKey("3"), "3");

		Set<String> allValues = map.get(new SimpleKey("0"));
		int setcount = allValues.size();
		
		
		RListMultimap<SimpleKey, String> map2 = redissonClient.getListMultimap("test1");
		map2.put(new SimpleKey("0"), "1");
		map2.put(new SimpleKey("0"), "11");
		map2.put(new SimpleKey("0"), "11");
		map2.put(new SimpleKey("0"), "111");
		map2.put(new SimpleKey("3"), "3");

		List<String> allValues2 = map2.get(new SimpleKey("0"));
		int listcount = allValues2.size();
		return "OK";
	}
}
