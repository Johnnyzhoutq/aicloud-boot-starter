package com.aicloud.boot.security.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aicloud.boot.security.entity.AicloudUser;
import com.aicloud.boot.security.repository.AicloudUserRepository;
import com.aicloud.boot.security.service.AicloudUserService;
@Service
@Transactional
public class AicloudUserServiceImpl implements AicloudUserService{
	@Resource
	private AicloudUserRepository userRepository;
	@Override
	public void save(AicloudUser user) {
		userRepository.save(user);
	}

	@Override
	public List<AicloudUser> findAll() {
		return userRepository.findAll();
	}

	@Override
	public AicloudUser findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}
