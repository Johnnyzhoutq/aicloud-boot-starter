package com.aicloud.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class OperationService {
	/** 
	* @ClassName: OperationService 
	* @Description: TODO
	* @author 周天琪 johnny_ztq@163.com
	* @date 2018年1月12日 下午4:44:34 
	*/
	private final Logger logger = LogManager.getLogger(getClass());
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String getMethodCall() {
    	logger.info("/api/getMethodCall, host:9021");
        return restTemplate.getForEntity("http://aicloud-cloud-service-1/api/getMethodCall", String.class).getBody();
    }

    public String addServiceFallback() {
        return "error";
    }
}
