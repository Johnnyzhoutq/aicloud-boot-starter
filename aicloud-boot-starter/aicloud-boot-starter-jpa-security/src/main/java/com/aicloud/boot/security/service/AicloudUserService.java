package com.aicloud.boot.security.service;

import java.util.List;

import com.aicloud.boot.security.entity.AicloudUser;

public interface AicloudUserService {
	public void save(AicloudUser user);
	public List<AicloudUser> findAll();
	/**
	 * 根据用户名查找用户实体
	 * @param userName
	 * @return
	 */
	public AicloudUser findByUserName(String userName);
}
