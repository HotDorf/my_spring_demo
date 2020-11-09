package com.example.annotation.manyThread.jichengThread;

public class ExtendsThread extends Thread{
    @Override
    public void run (){
        System.out.print("threastart,什么都做不了，不能传参，也没有返回，只能单继承Thread！");
    }
}
