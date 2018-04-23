package com.aicloud.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicloud.boot.jdbc.DataSource;
import com.aicloud.common.util.factory.IdGeneratorFactory;
import com.aicloud.entity.User;
import com.aicloud.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("save")
	@Transactional
	public String saveUser(){
			User user = new User();
			user.setName("事物测试");
			userService.save(user);
			int a = 4/0;
		return "Success";
	}
	
	@RequestMapping("all")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@RequestMapping("saveByAnotherDatabase")
	@DataSource("myDatasource1")
	public String saveUserByAnotherDatabase(){
		User user = new User();
		user.setName("张三");
		userService.save(user);
		return "Success";
	}
	
	@RequestMapping("testIdGeneretorFactory")
	public String testIdGeneretorFactory(){
		try {
			int n=1000000;
	    	IdGeneratorFactory idWorker = new IdGeneratorFactory(1, 1);  
	    	IdGeneratorFactory idWorker2 = new IdGeneratorFactory(2, 1);  
	        Set<Long> setOne = new HashSet<Long>();  
	        Set<Long> setTow = new HashSet<Long>();  
	        long start = System.currentTimeMillis();  
	        for (int i = 0; i < n; i++) {  
	            setOne.add(idWorker.nextId());//加入set  
	        }  
	        long end1 = System.currentTimeMillis() - start;  
	        System.out.println("第一批ID预计生成"+n+"个,实际生成"+setOne.size()+"个<<<<*>>>>共耗时:"+end1);  
	  
	        for (int i = 0; i < n; i++) {  
	            setTow.add(idWorker2.nextId());//加入set  
	        }  
	        long end2 = System.currentTimeMillis() - start;  
	        System.out.println("第二批ID预计生成"+n+"个,实际生成"+setOne.size()+"个<<<<*>>>>共耗时"+end2);  
	  
	        setOne.addAll(setTow);  
	        Iterator<Long> it = setOne.iterator();
	        while(it.hasNext()) {
	        	Long l = it.next();
	        	User user = new User();
	        	user.setUserId(l);
				user.setName("张三");
				userService.save(user);
	        }
	        System.out.println("合并总计生成ID个数"+setOne.size()); 
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "Success";
	}
}
