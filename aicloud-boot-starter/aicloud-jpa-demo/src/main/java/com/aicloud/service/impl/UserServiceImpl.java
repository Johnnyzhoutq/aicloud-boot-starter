package com.aicloud.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aicloud.entity.User;
import com.aicloud.repository.UserRepository;
import com.aicloud.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserRepository userRepository;
	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
