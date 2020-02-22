package com.demo.service.sys.impl;


import com.demo.dao.sys.UserSysDao;
import com.demo.entity.UserEntity;
import com.demo.service.sys.UserSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSysServiceImpl implements UserSysService {

	@Autowired
	private UserSysDao userDao;

	@Override
	public UserEntity findByLoginNameAndPass(String loginName, String password) {
		return userDao.findByLoginNameAndPass(loginName, password);
	}

	@Override
	public void insert(UserEntity user) {
		userDao.insertUser(user);
	}
}
