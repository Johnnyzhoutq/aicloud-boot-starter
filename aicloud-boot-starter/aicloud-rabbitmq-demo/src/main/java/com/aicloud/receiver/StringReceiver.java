package com.aicloud.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhoutianqi
 * @create 2017.6.2
 */
@Component
@RabbitListener(queues = "hello")
public class StringReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receive and handle a base object content: " + hello);
    }

}
