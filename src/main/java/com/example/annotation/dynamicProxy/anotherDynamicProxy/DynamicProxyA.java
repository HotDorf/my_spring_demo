package com.example.annotation.dynamicProxy.anotherDynamicProxy;

import org.springframework.stereotype.Service;

@Service
public class DynamicProxyA implements DynamicProxyB{

    @Override
    public String test01() {
        return "I am A";
    }
}
