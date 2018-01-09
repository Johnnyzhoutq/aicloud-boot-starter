package com.aicloud.service;

import java.util.List;

import com.aicloud.entity.User;

public interface UserService {
	public void save(User user);
	public List<User> findAll(); 
}
