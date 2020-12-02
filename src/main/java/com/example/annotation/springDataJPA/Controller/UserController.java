package com.example.annotation.springDataJPA.Controller;

import com.example.annotation.springDataJPA.service.UserService;
import com.example.annotation.springDataJPA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @RequestMapping("/queryPage")
    public void queryPage(){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name","illie");
        paramMap.put("age","19");
        paramMap.put("email","test5@baomidou.com");
        paramMap.put("pageNo","2");
        paramMap.put("pageSize","5");
        List<User> allUsers = userService.queryPage(paramMap);
        paramMap.replace("pageNo","3");
        List<User> allUsersThree = userService.queryPage(paramMap);
        System.out.println("第二页：=====");
        for (User allUser : allUsers) {
            System.out.println(allUser);
        }
        System.out.println("第三页：=====");
        for (User allUser : allUsersThree) {
            System.out.println(allUser);
        }
    }

    @RequestMapping("/queryPageWithoutSpecification")
    public void queryPageWithoutSprcifical() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNo", "0");
        paramMap.put("pageSize", "5");
        List<User> allUsers = userService.queryPageWithoutSprcifical(paramMap);
        paramMap.replace("pageNo", "1");
        List<User> allUsersTwo = userService.queryPageWithoutSprcifical(paramMap);
        System.out.println("第1页：=====");
        for (User allUser : allUsers) {
            System.out.println(allUser);
        }
        System.out.println("第2页：=====");
        for (User allUser : allUsersTwo) {
            System.out.println(allUser);
        }
    }

}
