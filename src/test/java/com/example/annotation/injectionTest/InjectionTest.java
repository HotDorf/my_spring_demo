package com.example.annotation.injectionTest;

import com.example.annotation.springInjection.AccountDao;
import com.example.annotation.springInjection.Config;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Field;
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class InjectionTest {

   @Resource(name = "accountDao", type = AccountDao.class)
    private AccountDao accountDao;

    /**
     * 用 注解前后关系配置类 来配置spring
     * 获取是否有被Autowired注解说明的字段，有的话就注入Bean
     */
    @Before
    public void injection() throws IllegalAccessException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        Class<? extends InjectionTest> injectionTest = this.getClass();
        Field[] declaredFields = injectionTest.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Autowired annotation = declaredField.getAnnotation(Autowired.class);
            if (annotation != null){
                declaredField.setAccessible(true);
                Object bean = context.getBean(declaredField.getType());
                declaredField.set(this,bean);
            }
        }
    }

    @Test
    public void test(){
        AccountDao accountDao = new AccountDao();
        accountDao.saveMoney();
        //accountDao.saveMoney();
        }
}
