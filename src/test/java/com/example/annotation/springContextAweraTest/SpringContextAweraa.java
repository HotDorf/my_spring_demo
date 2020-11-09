package com.example.annotation.springContextAweraTest;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.example.annotation.springInjection.AccountDao;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringContextAweraa {

    @Test
    public void test(){
        Object accountDao = SpringContextAweraTest.getBean("accountDao", AccountDao.class);
        System.out.println(accountDao.getClass().getName());
        Method[] declaredMethods = accountDao.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName()); //saveMoney
        }
    }

    @Test
    public void test01(){
        PrivateTest privateTest = SpringContextAweraTest.getBean("privateTest", PrivateTest.class);
        privateTest.t01();
    }

    @Autowired
    MessageSource messageSource;

    @Test
    public void test02(){
        String name = messageSource.getMessage("name", new Object[]{"年齡:20","性別：男"}, Locale.ENGLISH);

        System.out.println(" name (English) : " + name);

        String namechinese = messageSource.getMessage("name",
                new Object[] {"age:20","sex:man "},
                Locale.CHINESE);

        System.out.println(" name (Chinese) : " + namechinese);

    }
}