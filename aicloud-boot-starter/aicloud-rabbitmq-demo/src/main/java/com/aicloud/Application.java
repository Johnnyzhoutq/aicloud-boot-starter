package com.aicloud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicloud.entity.AicloudMessage;
import com.aicloud.sender.MessageSender;
import com.aicloud.sender.StringSender;
@SpringBootApplication
@RestController
public class Application{
	/**   
	* @Title: Application.java  
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author ZhouTianQi   
	* @date 2017年1月2日 下午3:49:01 
	* @version V1.0   
	*/
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}
	
	
	@Autowired
	private  StringSender sender;
	@Autowired
	private  MessageSender messageSender;
	@RequestMapping(value="/sender")
    public String logout1(){
		sender.send();
		return "sender ok";
    }
	@RequestMapping(value="/messageSender")
    public String logout2(){
		messageSender.sendToRabbitmq(new AicloudMessage("这是一个对象！"),"aaaaaaaaaaaaaa");
		return "messageSender ok";
    }
	@RequestMapping(value="/messagesSender")
    public String logout3(){
		List<AicloudMessage> list = new ArrayList<>();
		list.add(new AicloudMessage("这是一个m1对象！"));
		list.add(new AicloudMessage("这是一个m2对象！"));
		messageSender.sendListToRabbitmq(list,"list");
		return "messagesSender ok";
    }
}
