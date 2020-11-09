package com.example.annotation.springDataJPA.service;

import com.example.annotation.springDataJPA.dao.OperateUserDao;
import com.example.annotation.springDataJPA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    OperateUserDao userDao;

    public List<User> findAllUser(){
        return userDao.findAll();
    }
}
