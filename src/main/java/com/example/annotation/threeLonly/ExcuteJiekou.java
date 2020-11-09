package com.example.annotation.threeLonly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ExcuteJiekou {

    @Autowired
    @Qualifier("jiekouImpleTwo")
    JieKou jiekou;

    public void method01(){
        jiekou.method01();
    }

}
