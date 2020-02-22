package com.demo.service.sys;

import com.demo.entity.UserEntity;

public interface UserSysService {

	UserEntity findByLoginNameAndPass(String loginName, String password);

	void insert(UserEntity user);

}
