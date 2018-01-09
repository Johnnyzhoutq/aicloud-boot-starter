package com.aicloud.boot.kafka.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.MessagingMessageConverter;

@Configuration
public class AicloudKafkaAutoconfiguration {
	/**
	 * 这边放一个配置类，方便后续自动化配置
	*/
	@Autowired
	private KafkaTemplate<?, ?> kafkaTemplate;
	
	@Bean
	public MessagingMessageConverter getMessagingMessageConverter(){
		MessagingMessageConverter messagingMessageConverter = new MessagingMessageConverter();
		kafkaTemplate.setMessageConverter(messagingMessageConverter);
		return messagingMessageConverter;
	}
}
