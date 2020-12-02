package com.example.annotation.springDataJPA.dao;

import com.example.annotation.springDataJPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
*  JpaRepository 用于自动生成相关SQL语句简化代码，已经能满足基本的需要
*  JpaSpecificationExecutor 会有多一些的用法，条件查询、分页等等,所以有时候不写也不会报错
*  参考 https://veggie.blog.csdn.net/article/details/99675245
*  这里的 @Repository 注解不写也不影响JPA的使用
* */
public interface OperateUserDao extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {

    public List<User> findAll();

}
