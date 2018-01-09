package com.aicloud.boot.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色
 * @author zhoutianqi
 */
@Entity
@Table(name = "t_role")
public class AicloudRole {
	@Id
	@GeneratedValue
	private Integer id;
	/**
	 * 角色名称
	*/
	@Column(length=50)
    private String name;
	/**
	 * 用户状态 0：初始状态 4：删除状态
	*/
	private Integer state;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
