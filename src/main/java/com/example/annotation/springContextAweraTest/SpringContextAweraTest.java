package com.example.annotation.springContextAweraTest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *  獲取spring中的Bean，而通過ClassPathXmlApplicationContext來加載配置文件的方法
 *  都需要注入到spring容器中，这样才能获取bean
 */
@Component
public class SpringContextAweraTest implements ApplicationContextAware{
    private static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac=applicationContext;
    }

    public static <T> T getBean(String name,Class<T> classType){
        if (ac == null){
            System.out.println("ac is null !");
            return null;
        }
        return (T)ac.getBean(name,classType);
    }

    public static Object getBean(String name){
        if (ac == null){
            System.out.println("ac is null !");
            return null;
        }
        return ac.getBean(name);
    }
}
