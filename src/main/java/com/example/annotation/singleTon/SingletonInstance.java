package com.example.annotation.singleTon;

/**
 *  静态内部类生成单例，是线程安全
 */
public class SingletonInstance {
    //私有构造方法
    private SingletonInstance(){

    }

    private static class Builder{
        //声明成员变量
        private static SingletonInstance singletonInstance = new SingletonInstance();
    }
    //对外提供接口获取该实例
    public static SingletonInstance getSingletonInstance(){
        return Builder.singletonInstance ;
    }

}
