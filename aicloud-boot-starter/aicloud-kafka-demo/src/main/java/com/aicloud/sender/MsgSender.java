package com.aicloud.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.aicloud.entity.User;

@Component
public class MsgSender {
	/**
	* @author zhoutianqi
	* @className MsgSender.java
	* @date 2017年6月5日 下午4:05:19
	* @description kafka默认key、value序列化和反序列化都是Strng类型，可以自定义哦
	*/
	@Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
	
    public void send() {
        kafkaTemplate.send("aicloud-topic","testData-txt1");
        kafkaTemplate.send("aicloud-topic","testData-txt2");
    }

	public void sendUser() {
		User user = new User();
		user.setName("zhoutianqi");
		user.setUserId(1024);
		kafkaTemplate.send("aicloud-topic", user.toString());
	}
}
