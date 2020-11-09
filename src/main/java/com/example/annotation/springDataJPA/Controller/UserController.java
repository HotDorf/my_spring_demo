package com.example.annotation.springDataJPA.Controller;

import com.example.annotation.springDataJPA.service.UserService;
import com.example.annotation.springDataJPA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/findAll")
    public void findALlUser(){
        List<User> allUsers = userService.findAllUser();
        for (User allUser : allUsers) {
            System.out.println(allUser);
        }
    }


}
