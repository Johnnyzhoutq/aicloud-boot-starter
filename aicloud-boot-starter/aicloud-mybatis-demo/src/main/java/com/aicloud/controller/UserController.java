package com.aicloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicloud.entity.User;
import com.aicloud.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("save")
	public String saveUser(){
		User user = new User();
		user.setName("张三");
		userService.save(user);
		return "Success";
	}
	
	@RequestMapping("all")
	public List<User> findAll(){
		return userService.findAll(null,null);
	}
	
	@RequestMapping("one")
	public List<User> findOne(Integer page,Integer size){
		return userService.findAll(page,size);
	}
	
}
