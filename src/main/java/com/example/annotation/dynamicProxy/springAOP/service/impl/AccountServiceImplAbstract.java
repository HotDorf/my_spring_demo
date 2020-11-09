package com.example.annotation.dynamicProxy.springAOP.service.impl;

import org.springframework.stereotype.Component;

@Component
public class AccountServiceImplAbstract extends AccountServicePartImpl {
    @Override
    public void saveAccount() {
        System.out.println("AccountServiceImplAbstract执行了保存");
    }
}
