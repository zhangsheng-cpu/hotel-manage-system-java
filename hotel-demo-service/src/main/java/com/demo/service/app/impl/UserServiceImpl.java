package com.demo.service.app.impl;

import com.demo.dao.app.UserDao;
import com.demo.entity.UserEntity;
import com.demo.service.app.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public UserEntity findByLoginNameAndPass(String loginName, String passWord) {
        return dao.findByLoginNameAndPass(loginName, passWord);
    }

    @Override
    public UserEntity findByLoginName(String loginName) {
        return dao.findByLoginName(loginName);
    }

    @Override
    public void insertUser(UserEntity user) {
        dao.insertUser(user);
    }

}
