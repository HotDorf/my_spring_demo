package com.example.annotation.manyThread.threadPool;

import java.util.concurrent.*;

public class ThreadPoolUtils {

    public static ThreadPoolExecutor geThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (10, 11, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        return threadPoolExecutor;
    }

    public static void getCachedThreadPool(Runnable runnable) {

    }

    public static void getcheduledThreadPool(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟三秒");
            }
        }, 3, TimeUnit.SECONDS); //3s后执行一次

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟1秒后每三秒执行一次");
            }
        }, 1, 3, TimeUnit.SECONDS); //执行多次，1s后每3s执行一次
    }

    public static void main(String[] args) {
        getcheduledThreadPool();
    }

}
