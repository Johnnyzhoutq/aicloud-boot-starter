package com.aicloud.boot.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_role")
public class AicloudUserRole {
	/** 
	* @ClassName: AicloudUserRole 
	* @Description: TODO
	* @author 周天琪 johnny_ztq@163.com
	* @date 2018年1月9日 下午3:51:40 
	*/
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Integer userId;
	
	@Column
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
}
