package com.example.annotation.dynamicProxy.springAOP.service.impl;

import com.example.annotation.dynamicProxy.springAOP.service.IAccountService;
import org.springframework.stereotype.Service;

@Service
public abstract class AccountServicePartImpl implements IAccountService {

    public void updateAccount(int i) {
        System.out.println("执行了更新"+i);

    }

    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }

}
