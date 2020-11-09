package com.example.annotation.threeLonly;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class JiekouImpleTwo implements JieKou {
    @Override
    public void method01() {
        System.out.println("JiekouImpleTwo==:method01");
    }

    @Override
    public void method02() {
        System.out.println("JiekouImpleTwo==:method02");
    }

    @Override
    public void method03() {
        System.out.println("JiekouImpleTwo==:method03");
    }
}
