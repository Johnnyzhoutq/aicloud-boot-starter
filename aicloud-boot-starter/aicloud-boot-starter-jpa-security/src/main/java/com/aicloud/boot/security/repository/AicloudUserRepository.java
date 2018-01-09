package com.aicloud.boot.security.repository;

import org.springframework.data.jpa.repository.Query;

import com.aicloud.boot.jpa.QueryRepository;
import com.aicloud.boot.security.entity.AicloudUser;

public interface AicloudUserRepository extends QueryRepository<AicloudUser,Integer>{
	@Query(value="select * from t_user where user_name = ?1", nativeQuery=true)
	public AicloudUser findByUserName(String userName); 
}
