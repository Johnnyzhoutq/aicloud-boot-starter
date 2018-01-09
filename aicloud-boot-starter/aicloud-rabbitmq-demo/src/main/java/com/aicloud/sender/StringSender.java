package com.aicloud.sender;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @author zhoutianqi
 * @create 2017.6.2
 */
@Component
public class StringSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send() {
		String context = "hello " + new Date();
		System.out.println("Send a base object content by rabbitTemplate: " + context);
		this.rabbitTemplate.convertAndSend("hello", context);
	}

}