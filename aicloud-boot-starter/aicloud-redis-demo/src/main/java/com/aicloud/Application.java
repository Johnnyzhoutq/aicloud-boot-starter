package com.aicloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicloud.entity.User;
@SpringBootApplication
@RestController
public class Application{
	/**   
	* @Title: Application.java  
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author ZhouTianQi   
	* @date 2017年1月2日 下午3:49:01 
	* @version V1.0   
	*/
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}
	
	@Autowired
	private RedisTemplate<String, User> redisTemplate;
	
	@RequestMapping(value = "test")
	public String testRedis1(){
		redisTemplate.opsForValue().set("zhoutianqi", new User("zhoutianqi","123456"));
		
		User currentUser = redisTemplate.opsForValue().get("zhoutianqi");
		System.out.println("Redis存储对象："+currentUser.toString());
		return "OK";
	}
}
