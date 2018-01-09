package com.aicloud.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoutianqi
 * @create 2017.6.2
 */
@Configuration
public class AicloudRabbitQueueConfiguration{
	/*
	 * RabbitMQ默认是不持久队列、Exchange、Binding以及队列中的消息的，这意味着一旦消息服务器重启，
	   所有已声明的队列，Exchange，Binding以及队列中的消息都会丢失。
	   通过设置Exchange和MessageQueue的durable属性为true，可以使得队列和Exchange持久化，
	   但是这还不能使得队列中的消息持久化，还需要生产者在发送消息的时候，
	   将basicPublish()方法的BasicProperties props参数中deliveryMode设置为2【使用basicPublish(MessageProperties.PERSISTENT_BASIC)】，
	   只有这3个全部设置完成后，才能保证服务器重启不会对现有的队列造成影响。
	 
	 RabbitTemplete生成的消息实际上默认是持久化的 所以只需要Exchange和Queues是持久化的，即可持久化
	   这里需要注意的是，只有durable为true的Exchange和durable为ture的Queues才能绑定，
	   否则在绑定时，RabbitMQ都会抛错的。持久化会对RabbitMQ的性能造成比较大的影响，可能会下降10倍不止。
	*/
	
	@Bean
	Queue queueHello() {
		return new Queue("hello");
	}
	
	//定义名称是queue.message的消息队列，false不开启消息持久化
	@Bean
	Queue queueMessage() {
		return new Queue("queue.A", false);
	}
	//定义名称是queue.messages的消息队列，false不开启消息持久化
	@Bean
	Queue queueMessages() {
		return new Queue("queue.B", false);
	}
	//定义数据交换器，当然呢有四种类型，根据需求来
	@Bean
	TopicExchange mess1exchange() {
		return new TopicExchange("A");
	}
	@Bean
	TopicExchange mess2exchange() {
		return new TopicExchange("B");
	}
	@Bean
	Binding bindingExchangeMessage() {
		return BindingBuilder.bind(queueMessage()).to(mess1exchange()).with("queue.A.bind");
	}

	@Bean
	Binding bindingExchangeMessages() {
		return BindingBuilder.bind(queueMessages()).to(mess2exchange()).with("queue.B.bind");
	}

}
