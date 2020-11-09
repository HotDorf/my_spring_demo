package com.example.annotation.manyThread.jichengThread;

import com.example.annotation.manyThread.threadPool.ThreadPoolExcutorTest;
import com.example.annotation.manyThread.threadPool.ThreadPoolUtils;
import com.example.annotation.poi.ExcelUtil;
import com.example.annotation.poi.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ImplementCallable extends UseThread implements Callable {

    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 100; i++) {
            data();
        }
        return "完成了！";
    }

    //使用线程池
    public static void useCallable() {
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

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        useCallable();
        long l3 = System.currentTimeMillis()- l1;
    }
}
