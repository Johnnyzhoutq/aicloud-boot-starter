package com.aicloud.boot.security.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aicloud.boot.security.entity.AicloudRole;
import com.aicloud.boot.security.repository.AicloudRoleRepository;
import com.aicloud.boot.security.service.AicloudRoleService;
@Service
@Transactional
public class AicloudRoleServiceImpl implements AicloudRoleService{
	@Resource
	private AicloudRoleRepository roleRepository;

	@Override
	public List<AicloudRole> findByUserId(Integer userId) {
		return roleRepository.findByUserId(userId);
	}

}
