package com.aicloud.boot.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.aicloud.boot.jpa.QueryRepository;
import com.aicloud.boot.security.entity.AicloudRole;
import com.aicloud.boot.security.entity.AicloudUserRole;

public interface AicloudRoleRepository extends QueryRepository<AicloudUserRole,Integer>{
	@Query(value="select * from t_role r where r.id in (select ur.role_id from t_user_role ur where ur.user_id=?1)", nativeQuery=true)
	public List<AicloudRole> findByUserId(Integer userId);
}
