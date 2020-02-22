package com.demo.dao.app;

import com.demo.entity.UserEntity;

public interface UserDao {

    UserEntity findByLoginNameAndPass(String loginName, String passWord);

    UserEntity findByLoginName(String loginName);

    void insertUser(UserEntity user);
}
