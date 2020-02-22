package com.demo.service.app;

import com.demo.entity.UserEntity;

public interface UserService {

    UserEntity findByLoginNameAndPass(String loginName, String passWord);

    UserEntity findByLoginName(String loginName);

    void insertUser(UserEntity user);
}
