package com.example.annotation.manyThread.jichengThread;

import com.example.annotation.springContextAweraTest.SpringContextAweraTest;
import com.example.annotation.utils.DateOperate;
import com.sun.xml.bind.v2.util.FatalAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UseThread {
    /*public static void main(String[] args) {
        //threadTest(); //继承Thread执行的效果
        //implementRunableTest();
        //data();
    }*/

    public static void data(){
        long parse = System.currentTimeMillis();  //获取当前时间，转换成微妙值
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format1 = simpleDateFormat1.format(new Date(parse)); //这两句是为了获得format1的值，是 2020-01-06 20:40:30.899
        System.out.println(format1);

        for (int i = 0; i < 3; i++) {
            System.out.println("外面循环第"+i+"次++++");
            parse += 24*60*60*1000*1;  // 循环一次加一天，这里用的是微妙值
            for (int j = 0; j < 2; j++) {
                System.out.println("业务第"+j+"次循环"); //这里是你的业务
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format = simpleDateFormat.format(new Date(parse)); //和上面一样的操作，format值是 2020-01-09 20:40:30.899
        System.out.println(format);
    }

    public static void implementRunableTest(){
        ImplementRunable implementRunable = new ImplementRunable();
        Thread thread = new Thread(implementRunable);
        thread.start();
    }

    public static void threadTest(){
        for (int i = 0; i < 20; i++) {
            ExtendsThread extendsThread = new ExtendsThread();
            extendsThread.start();
            System.out.println(" === " + i);
        }
    }

}
