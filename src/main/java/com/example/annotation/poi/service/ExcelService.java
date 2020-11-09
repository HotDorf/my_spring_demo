package com.example.annotation.poi.service;

import com.example.annotation.mybatisplus.entities.entity.MybatisplusUser;
import com.example.annotation.mybatisplus.service.UserEntityService;
import com.example.annotation.poi.Constant;
import com.example.annotation.poi.ExcelUtil;
import com.example.annotation.poi.Person;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class ExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

    @Autowired
    UserEntityService userEntityService;

    public void exportExcel() {
        List<MybatisplusUser> mybatisplusUsers = userEntityService.testSelect();
        XSSFWorkbook workbook =
                ExcelUtil.getWorkbook(Constant.mybatisplusUsersSheet, Constant.mybatisplusUsersTitles, mybatisplusUsers);
        //声明输出流
        FileOutputStream output = null;
        File file = new File("C:\\Users\\HotDorf\\Documents\\excel\\excel" + System.currentTimeMillis() + ".xlsx");
        try {
            output = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            logger.error("",e.getMessage());
        }
        System.out.println("导出的路径在："+file.getPath());

        //写出
        try {
            workbook.write(output);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readExcel() throws IOException {
        File file = new File("C:\\Users\\HotDorf\\Documents\\excel\\excel.xlsx");
        FileInputStream input = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", file.getName(), "application/octet-stream", input);
        List<Person> peoples = ExcelUtil.readExcel(mockMultipartFile, Person.class);
        for (Person person : peoples) {
            System.out.println(person);
        }
    }

}
