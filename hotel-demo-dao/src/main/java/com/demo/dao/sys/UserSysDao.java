package com.demo.dao.sys;

import com.demo.entity.UserEntity;

public interface UserSysDao {

    UserEntity findByLoginNameAndPass(String loginName, String password);

    void insertUser(UserEntity user);
}
