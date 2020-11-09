package com.example.annotation.springInjection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public User configUser(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setAge(13);
        return user;
    }

}
