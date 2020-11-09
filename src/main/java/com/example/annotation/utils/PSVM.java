package com.example.annotation.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.annotation.poi.Person;
import com.example.annotation.utils.duojicaidang.Menu;
import org.springframework.beans.BeanUtils;
import springfox.documentation.spring.web.json.Json;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  switch case 要加break
 */
public class PSVM {
    public static void main(String[] args) {
        //digui(4);
        //localDateTime();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a","a");
        System.out.println(hashMap + "============");
        hashMap.replace("a","b");
        System.out.println(hashMap+"============");
    }

    private static void objectJson(){
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("name","zhangsan");
        map.put("email","163@.com.cn");
        map.put("age",123);
        JSONObject jsonObject = new JSONObject(map);
        JSONArray age1 = jsonObject.getJSONArray("age");
        ObjectJsonClass objectJsonClass = JSON.parseObject(JSON.toJSONString(jsonObject), ObjectJsonClass.class);

    }


    private static void localDateTime(){

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

    // 这个方法unless的字段 source类和target类都可以
    private static void copyProperties(){
        Person person = new Person();
        person.setName("zhangsan");
        Menu menu = new Menu();
        BeanUtils.copyProperties(person,menu,"id","parentId","url","icon","order","children");
        //BeanUtils.copyProperties(person,menu,"number","age","sex");
        System.out.println(menu);
    }

    private static void caseSwitch(){
        Integer x = 10;
        Integer y = 10;
        System.out.println("(int)x/y = " + (int)x/y);

        int z = (int)x/y;

        switch (z){
            case 1:
                x += 1;
            case 2:
                x +=2;
            default:
                x+=3;
        }
        System.out.println( "x = " + x);
        Integer a = 110, b = 120, c = 130, d =160,xy = x*y;
        if (xy.equals(a)){
            System.out.println("a");
        } else if (xy.equals(b)){
            System.out.println("b");
        } else if (xy.equals(c)){
            System.out.println("c");
        } else if (xy.equals(d)){
            System.out.println("d");
        } else {
            System.out.println("e");
        }
    }

    private static int digui(int n) {
        if (n >= 0) {
            if (n == 0) {
                System.out.println(n + "!=1");
                return 1;
            } else {
                System.out.println(n);
                int temp = n * digui(n - 1);
                System.out.println(n + "!=" + temp);
                return temp;
            }
        }
        return -1;
    }

}
