package com.example.annotation.mybatisplus.pojo;

import lombok.Data;

@Data
public class BigPerson {
    private Integer id;

    /**
     * 名字
     */
    private String name;

    private Integer age;

    private String email;

    private String remake1;

    private String remake2;
}
