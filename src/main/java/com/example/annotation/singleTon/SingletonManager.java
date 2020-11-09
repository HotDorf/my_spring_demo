package com.example.annotation.singleTon;

import java.util.HashMap;
import java.util.Map;

public class SingletonManager {
    private static Map<String, Object> objMap = new HashMap<String, Object>();//使用HashMap作为缓存容器

    public static void registerService(String key, Object instance) {
        if (!objMap.containsKey(key)) {
            objMap.put(key, instance);//第一次是存入Map
        }
    }

    //返回与key相对应的对象
    public static Object getObject(String key) {
        return objMap.get(key);
    }
}