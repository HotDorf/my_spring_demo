package com.example.annotation.dynamicProxy.anotherDynamicProxy;

public class DynamicProxyC implements DynamicProxyB {
    @Override
    public String test01() {
        return "I am C";
    }

    // 在C中执行A
    public void test02(){
        IncreaseResult increaseResult = new IncreaseResult();
        DynamicProxyB proxy = increaseResult.getProxy(new DynamicProxyA());
        String s = proxy.test01();
        System.out.println( s + " 把这个抽象方法处理了一下============= ");
    }

}
