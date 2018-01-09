package com.aicloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicloud.sender.MsgSender;

@RestController
public class KafkaController {
	/**
	* @author zhoutianqi
	* @className KafkaController.java
	* @date 2017年6月5日 下午4:35:02
	* @description 
	*/
	@Autowired
	private MsgSender msgSender;
	@RequestMapping(value = "msgSender")
	public String testKafka(){
		msgSender.send();
		return "OK";
	}
	
	@RequestMapping(value = "userSender")
	public String testUserKafka(){
		msgSender.sendUser();
		return "OK";
	}

}
