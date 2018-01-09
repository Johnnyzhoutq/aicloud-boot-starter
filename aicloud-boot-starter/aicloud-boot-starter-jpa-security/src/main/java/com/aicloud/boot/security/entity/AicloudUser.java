package com.aicloud.boot.security.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "t_user")
public class AicloudUser {
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotEmpty(message="请输入用户名！")
	@Column(length=50)
	private String userName;
	
	@NotEmpty(message="请输入密码！")
	@Column(length=50)
	private String password;
	
	@Column(length=50)
	private String email;
	
	@Column(length=11)
	private String phone;
	/**
	 * 用户状态 0：初始状态 4：删除状态
	*/
	@Column(length = 5)
	private Integer state;
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	/**
	 * 更新时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	//缓存用户所有店铺
	@Transient
	private List<AicloudRole> roleList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public List<AicloudRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<AicloudRole> roleList) {
		this.roleList = roleList;
	}
	
}
