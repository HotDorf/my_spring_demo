package com.example.annotation.dynamicProxy.springAOP.service.impl;

import org.springframework.stereotype.Component;

@Component
public class A extends AccountServicePartImpl{
    @Override
    public void saveAccount() {
        System.out.println(" A 执行了保存");
    }
}
