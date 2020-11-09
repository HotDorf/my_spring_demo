package com.example.annotation.singleTon;

/**
 *  多线程的话加一个锁就好了
 */
public class Printer {

    private static Printer printer =null;//创建一个私有的全局变量

    /*
     * 如果有多线程并发访问时，上锁，让其排队等候，一次只能一人用。
     */
    public static synchronized Printer getPrinter(){
        if(printer==null){//如果为空，创建本实例
            printer = new Printer();
        }
        return printer;
    }

    /*
     * 构造私有化，保证在系统的使用中，只有一个实例
     */
    private Printer(){
    }

}