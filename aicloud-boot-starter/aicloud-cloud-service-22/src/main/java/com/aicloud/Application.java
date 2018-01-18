package com.aicloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication
public class Application {
	/**   
	* @Title: Application.java  
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author ZhouTianQi   
	* @date 2017年1月2日 下午3:49:01 
	* @version V1.0   
	*/
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
