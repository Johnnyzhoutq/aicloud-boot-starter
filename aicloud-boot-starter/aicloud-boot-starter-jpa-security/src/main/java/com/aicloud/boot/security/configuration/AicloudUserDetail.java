package com.aicloud.boot.security.configuration;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.aicloud.boot.security.entity.AicloudRole;

@SuppressWarnings("serial")
public class AicloudUserDetail extends User{
	/** 
	* @ClassName: AicloudUserDetail 
	* @Description: 敲黑板！！！这玩意就是传说中的认证管理器中存储的用户信息
	* @author 周天琪 johnny_ztq@163.com
	* @date 2018年1月9日 下午4:10:03 
	*/
	public AicloudUserDetail(Integer id,String username, String password,
			Collection<? extends GrantedAuthority> authorities){
		super(username,password,authorities);
		this.id=id;
	}
	
	public AicloudUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Integer id, List<AicloudRole> roleList) {
		super(username, password, authorities);
		this.id = id;
		this.roleList = roleList;
	}
	
	private Integer id;
	
	private List<AicloudRole> roleList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public List<AicloudRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<AicloudRole> roleList) {
		this.roleList = roleList;
	}
	
	
}
