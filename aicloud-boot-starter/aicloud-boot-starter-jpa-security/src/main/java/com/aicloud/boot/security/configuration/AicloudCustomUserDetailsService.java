package com.aicloud.boot.security.configuration;
/**
 * Security 根据用户名 userName 获取数据库实例以及实例对应角色
 * @author 周天琪 johnny_ztq@163.com
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.aicloud.boot.security.entity.AicloudRole;
import com.aicloud.boot.security.entity.AicloudUser;
import com.aicloud.boot.security.service.AicloudRoleService;
import com.aicloud.boot.security.service.AicloudUserService;
@Component
public class AicloudCustomUserDetailsService implements UserDetailsService{
	/**
	 * Security 根据用户名 userName 获取数据库实例以及实例对应角色
	 * 1.实例不存在		抛出异常
	 * 2.实例存在		获取实例对应的角色，并添加到authorities
	 * 3.然后返回AicloudUserDetail实体，开始后台验证
	 */
	@Autowired
	private AicloudUserService userService;
	@Autowired
	private AicloudRoleService roleService;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AicloudUser user = null;
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();;
		try {
			System.out.println("接口被调用");
			user = userService.findByUserName(userName);
			if(user == null){
	        }
			List<AicloudRole> roleList = roleService.findByUserId(user.getId());
	        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
			for(AicloudRole role:roleList)
	        {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	            System.out.println("当前用户角色："+role.getName());
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AicloudUserDetail(user.getId(), user.getUserName(), user.getPassword(), authorities);
	}
	
}
