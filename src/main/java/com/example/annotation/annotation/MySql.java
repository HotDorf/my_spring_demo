package com.example.annotation.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MySql {

    String value() default " ";

    String username() default "zhangsan";

}
