package com.example.annotation.enums;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 在类中使用枚举
 *
 */
@Component
public class MyEnum implements Serializable{

    private static final long serialVersionUID = -2818535926293339386L;

    private String beNeed;

    private int index;

    public String getBeNeed() {
        return beNeed;
    }

    public void setBeNeed(String beNeed) {
        this.beNeed = beNeed;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static enum userEnum{

        ZHANG_SANG("zhangsan",18),
        LI_SI("lisi",22),
        WANG_WU("wangwy",52);

        private String name;

        private int age;

        userEnum(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public static String getName(int age){
            for(userEnum c : userEnum.values()){
                if(c.getAge() == age){
                    return c.name;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


}
