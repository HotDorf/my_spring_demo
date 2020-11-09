package com.example.annotation.manyThread;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class ThreadUtil {

    private static int value1 = 0;
    private static int value2 = 0;//悲观锁
    private static AtomicInteger value3 = new AtomicInteger(0);

    public static synchronized void increaseValue2(){
        value2++;
    }
        public static void main(String[] args) {
            for (int i = 0; i < 1000; i++) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        value1++;
                        increaseValue2();
                        value3.getAndIncrement();
                    }
                }.start();
            }
            while (Thread.activeCount() > 2) {//用来查看当前活跃线程
                Thread.yield();
            } //idea里面有一个监控线程，主线程
            System.out.println("线程不安全："+value1);
            System.out.println("悲观锁："+value2);
            System.out.println("乐观锁："+value3);

        }
}
