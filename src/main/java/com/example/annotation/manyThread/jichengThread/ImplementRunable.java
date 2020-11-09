package com.example.annotation.manyThread.jichengThread;

import com.example.annotation.poi.service.ExcelService;
import com.example.annotation.utils.DateOperate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ImplementRunable extends UseThread implements Runnable {
    @Override
    public void run() {
        //data();
        System.out.println("开新线程干活了！");
    }

}
