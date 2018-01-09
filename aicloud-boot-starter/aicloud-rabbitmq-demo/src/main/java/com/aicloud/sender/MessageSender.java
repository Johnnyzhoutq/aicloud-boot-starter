package com.aicloud.sender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aicloud.entity.AicloudMessage;
@Component
public class MessageSender implements RabbitTemplate.ConfirmCallback{
	/**
	* @author zhoutianqi
	* @className MessageSender.java
	* @date 2017年6月2日 下午3:37:04
	* @description 
	*/
	//默认已支持对复杂对象的操作
	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;

	public void sendToRabbitmq(AicloudMessage host, String extraContent){
		
		Map<String, Object> header = new HashMap<>();
		header.put("extraContent", extraContent);

		this.rabbitMessagingTemplate.convertAndSend("A", "queue.A.bind", host, header);
		System.out.println("Send a customized object message to [queue.A] by RabbitMessagingTemplate");
		String a = this.rabbitMessagingTemplate.convertSendAndReceive("A", "queue.A.bind", host, String.class);
		System.out.println("Send a customized object message to [queue.A] by RabbitMessagingTemplate");
	}
	
	public void sendListToRabbitmq(List<AicloudMessage>  listHost,  String extraContent){
		
		Map<String, Object> header = new HashMap<>();
		header.put("extraContent", extraContent);
		this.rabbitMessagingTemplate.convertAndSend("B", "queue.B.bind", listHost, header);
		System.out.println("Send a customized object list message to [queue.B] by RabbitMessagingTemplate");
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		// TODO Auto-generated method stub
		System.out.println(" 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息成功消费");
        } else {
            System.out.println("消息消费失败:" + cause+"\n重新发送");

        }
	}
}
