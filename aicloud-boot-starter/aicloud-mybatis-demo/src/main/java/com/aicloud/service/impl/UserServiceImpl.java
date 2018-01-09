package com.aicloud.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aicloud.boot.mybatis.core.AbstractService;
import com.aicloud.entity.User;
import com.aicloud.service.UserService;
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
	
}
