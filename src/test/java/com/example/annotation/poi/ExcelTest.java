package com.example.annotation.poi;

import com.alibaba.fastjson.JSON;
import com.example.annotation.poi.service.ExcelService;
import com.example.annotation.utils.duojicaidang.AllSonNodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest{

    @Autowired
    ExcelService excelService;

    @Autowired
    AllSonNodeService allSonNodeService;


    @Test
    public void caidanGet(){
        Map<String, Object> tree = allSonNodeService.findTree();
        System.out.println(JSON.toJSON(tree));
    }

    // 导出
    @Test
    public void exportTest(){
        //Object excelUtil = SpringContextAweraTest.getBean("ExcelUtil");
        excelService.exportExcel();
    }

    // 导入读取表格数据
    @Test
    public void readExcel() throws IOException {
        excelService.readExcel();
    }




}
