package com.example.annotation.singleTon;

/**
 * 枚举可以保证反序列化的时候也是单例，枚举默认就是线程安全的
 */
public enum  SingleTonEmue {
        INSTANCE;
}
