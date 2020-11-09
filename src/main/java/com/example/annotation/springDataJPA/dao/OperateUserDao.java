package com.example.annotation.springDataJPA.dao;

import com.example.annotation.springDataJPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperateUserDao extends JpaRepository<User,Integer> {

    public List<User> findAll();

}
