package com.example.annotation.annotation;

import org.springframework.stereotype.Component;

/**
 * 解析注解
 *
 */
@Component
@MySql(value = "sql", username = "lisi")
public class AnalysisAnno {
    public static void main(String[] args) {
        Class<AnalysisAnno> analysisClass = AnalysisAnno.class;
        MySql mysql = analysisClass.getAnnotation(MySql.class);
        System.out.println(mysql.value());
        System.out.println(mysql.username());

    }
}
