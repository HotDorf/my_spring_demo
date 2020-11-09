package com.example.annotation.manyThread.threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExcutorTest2 implements Runnable {
  
    public Integer count;  
      
    public ThreadPoolExcutorTest2(Integer count) {  
        this.count = count;  
    }  
      
    @Override  
    public void run() {  
        System.out.println("任务" + count);  
        try {  
            Thread.sleep(1000);
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static void main(String[] args) throws InterruptedException {  
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();  //无界队列
          
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.SECONDS, workQueue);
        for (int i = 1; i <= 20; i++) {  
            pool.execute(new ThreadPoolExcutorTest2(i));  
        }  
        Thread.sleep(1000);
        System.out.println("线程池中队列中的线程数量：" + workQueue.size());
        pool.shutdown();  
    }
    /*
    * 使用无界队列，那么maximumPoolSize就失去了意义。
    * 当线程数达到corePoolSize，再来的任务会放在无界队列中。无界队列满不了，就不会再创建线程去靠近maximumPoolSize量。
    * 队列中可以放很多任务直到系统资源不够用。
    * */
}  