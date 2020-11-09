package com.example.annotation.dynamicProxy.anotherDynamicProxy;

import com.example.annotation.dynamicProxy.JDKproxy.IProducer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  继承同一个接口有两个类，在其中一个类生成另一个类的 代理对象，执行另一个类的实现方法
 *
 */
@SuppressWarnings("all")
public class IncreaseResult {

    public <T> DynamicProxyB getProxy(T t) {

        Object target = null;
        try {
            target = t.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Object finalTarget = target;
        DynamicProxyB proxyProducer = (DynamicProxyB) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //提供增强的代码
                        Object returnValue = null;
                        //1.获取方法执行的参数
                        // Float money = (Float)args[0];
                        //2.判断当前方法是不是销售
                        returnValue = method.invoke(finalTarget, args); //producer需要final修饰
                        return returnValue;
                    }
                });
        return proxyProducer;
    }
}
