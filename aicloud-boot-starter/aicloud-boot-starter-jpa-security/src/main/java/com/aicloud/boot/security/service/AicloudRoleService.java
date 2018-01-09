package com.aicloud.boot.security.service;

import java.util.List;

import com.aicloud.boot.security.entity.AicloudRole;

public interface AicloudRoleService {
	/**
	 * 根据用户ID查找角色实体
	 * @param userName
	 * @return
	 */
	List<AicloudRole> findByUserId(Integer userId);
}
