package com.example.annotation.springAOPTest;

import com.example.annotation.dynamicProxy.springAOP.service.IAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringAOPTest {

    @Autowired
    @Qualifier("accountServiceImplAbstract")
    IAccountService accountService;

    @Test
    public void test(){
        accountService.saveAccount();
    }

}
