package com.example.annotation.manyThread.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExcutorTest implements Runnable {
      
      
    public String name;  
      
    public ThreadPoolExcutorTest(String name) {  
        this.name = name;  
    }  
      
    @Override  
    public void run() {  
        System.out.println(name);  
        try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
          
    }  
      
    public static void main(String[] args) {  
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3); //有界队列，容量是3个submit
        /*
        * BlockingQueue<Runnable> workQueue:是一个阻塞队列，当线程达数达到maximumPoolSize之后仍有待执行的任务，就会放在这个队列里。
        *                                   有三种类型的阻塞队列 ArrayBlockingQueue;LinkedBlockingQueue; SynchronousQueue;
        */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                            1, //corePoolSize  
                            2,  //maximumPoolSize  
                            1L,  
                            TimeUnit.SECONDS,
                            workQueue  
                            );  
        threadPool.execute(new ThreadPoolExcutorTest("任务1"));  
        threadPool.execute(new ThreadPoolExcutorTest("任务2"));  
        threadPool.execute(new ThreadPoolExcutorTest("任务3"));  
        threadPool.execute(new ThreadPoolExcutorTest("任务4"));  
        threadPool.execute(new ThreadPoolExcutorTest("任务5"));  
        threadPool.execute(new ThreadPoolExcutorTest("任务6"));  
        threadPool.shutdown();  
        /*
        * 执行结果：任务6没执行。
        * 执行过程分析：线程池的corePoolSize为1，任务1提交后，线程开始执行，corePoolSize 数量用完。
        * 接着任务2、3、4提交，放到了有界队列中，此时有界队列也满了。
        * 继续提交任务5，由于当前运行的线程数poolSize < maximumPoolsize,线程池尝试new一个新的线程来执行任务5，所以任务5会接着执行。
        * 当继续提交任务6,时，poolSize达到了maximumPoolSize，有界队列也满了，所以线程池执行了拒绝操作。
        * */
    }  
  
  
}  