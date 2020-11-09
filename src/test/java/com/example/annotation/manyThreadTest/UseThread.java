package com.example.annotation.manyThreadTest;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.annotation.manyThread.jichengThread.ExtendsThread;
import com.example.annotation.manyThread.jichengThread.ImplementCallable;
import com.example.annotation.manyThread.jichengThread.ImplementRunable;
import com.example.annotation.manyThread.threadPool.ThreadPoolUtils;
import com.example.annotation.poi.service.ExcelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UseThread {
/*
    public static void main(String[] args) {
        //threadTest(); //继承Thread执行的效果
        implementRunableTest();
    }
*/
    @Autowired
    ExcelService excelService;

    @Test
    public void useCallable() {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtils.geThreadPool();
        threadPoolExecutor.execute(new ImplementRunable());

        ImplementCallable implementCallable = new ImplementCallable();
        Future future = threadPoolExecutor.submit(implementCallable);

        try {
            System.out.println(future.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
    }

    @Test
    public void implementRunableTest(){
        ImplementRunable implementRunable = new ImplementRunable();
        Thread thread = new Thread(implementRunable);
        thread.start();
    }

    @Test
    public void threadTest(){
        for (int i = 0; i < 20; i++) {
            ExtendsThread extendsThread = new ExtendsThread();
            extendsThread.start();
            System.out.println(" === " + i);
        }
    }

}
