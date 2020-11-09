package com.example.annotation.utils;

import com.example.annotation.mybatisplus.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class Datautil {

    @Autowired
    private UserEntityService userEntityService;

    //静态初始化
    private static Datautil datautil;

    //在方法上加上注解@PostConstruct，保证Bean初始化前已经装配了属性（注：Bean初始化包括，实例化Bean，并装配Bean的属性（依赖注入））
    @PostConstruct
    public void init() {
        datautil = this;
    }

    public void getAll(){
        if (Objects.isNull(userEntityService)){
            System.out.println("错误！");
        }
        System.out.println(datautil.userEntityService.testSelect());
    }
}
