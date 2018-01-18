package com.aicloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicloud.boot.jdbc.DataSource;
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
}
