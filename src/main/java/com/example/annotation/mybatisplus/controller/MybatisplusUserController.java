package com.example.annotation.mybatisplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.annotation.mybatisplus.service.UserEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Api(tags = {"mybatisplus控制层 "})
public class MybatisplusUserController {

    @Autowired
    @Qualifier("userEntityService")
    private UserEntityService userServer;

    @RequestMapping("selectAll")
    @Transactional
    public void selectAll(){
        userServer.testSelect();
    }

    @PostMapping("/test01")
    @ApiOperation(value = "测试1", notes = "测试1", response = JSONObject.class, produces = "application/json;charset=UTF-8", httpMethod = "POST")
    public Termin est(){
        return new Termin((long)23);
    }

}
