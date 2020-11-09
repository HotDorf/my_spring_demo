package com.example.annotation.exception;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

@Slf4j
public class WorkTest {
    public static void main(String[] args) {

        log.info(MessageFormatter.arrayFormat("da[{}]",new Object[]{"AAA"}).getMessage());
        log.info(String.format("[%s]","sa"));

        try {
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("sasd");
        }
        System.out.println(123);

    }


}
