package com.aicloud.receiver;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.aicloud.entity.AicloudMessage;

@Component
public class MessageReceiver {
	/**
	* @author zhoutianqi
	* @className MessageReceiver.java
	* @date 2017年6月2日 下午3:38:39
	* @description 
	*/
	@RabbitListener(queues = "queue.A")
	@RabbitHandler
	public void receiveMessage(AicloudMessage message) {
		System.out.println("queue A 收到消息 : Received message<"+message.getText() + ">");  
	}
	
	@RabbitListener(queues = "queue.B")
	@RabbitHandler
	public void receiveMessages(List<AicloudMessage>  listHost) {
		System.out.println("queue B-> Received list message number<" + listHost.size() + ">");
	}
}
