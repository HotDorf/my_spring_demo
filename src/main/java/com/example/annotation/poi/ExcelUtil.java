package com.example.annotation.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.*;

/**
 * 自定义工具类，用于读写Excel2007文件
 *
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 按从左至右的顺序读取所有表头和对应内容,读取的表中的数据都是String类型
     * constant字段中没有的就会跳过，第一个是表头内容，第二个是实体field
     *
     * @param file multipartFile文件
     * @param t 读取内容的封装类，一行Excel一个实例
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> readExcel(MultipartFile file, Class<T> t) throws IOException {
        ArrayList<T> people = new ArrayList<>();
        String originalFilename = file.getOriginalFilename();
        System.out.println("表名："+ originalFilename);
        //得到所需sheet
        InputStream inputStream = file.getInputStream();
        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        //获取sheet中的数据边界
        int lastRowNum = sheet.getLastRowNum();
        int lastCellNum = sheet.getRow(0).getLastCellNum();

        String[] head2Content = Constant.head2Content.split(";");
        for (int i = 1; i < lastRowNum; i++) {
            // 一行一行的处理
            Row row = sheet.getRow(i);
            // 每一行是一个实体类
            T newInstance = null;
           try {
                newInstance = (T) t.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                logger.info("传入实体类没有空参构造!",e.getMessage());
            }
            for (int j = 0; j < lastCellNum; j++) {
                // 将一行数据装进一个实体类中,根据表头来确定对应的实体类字段，Excel读取顺序是从左至右，所有表头都有字段对应
                String[] fields = head2Content[j].split(","); // 实体类对应字段
                if (1 == fields.length){
                    continue;
                }
                // 处理字段类型,先将cellType设置成String，再根据实体数据类型单独处理
                setFieldValue(fields[1],getCellValue(row.getCell(j)),newInstance);
            }
            people.add(newInstance);

        }
        return  people;
    }

    public static <T> void setFieldValue(String field,String value,T t){
        Field declaredField = null;
        try {
            declaredField = t.getClass().getDeclaredField(field);
            declaredField.setAccessible(true);
            String fieldTypeName = declaredField.getGenericType().getTypeName();
            switch (fieldTypeName){
                case "java.lang.String":
                    declaredField.set(t, value);
                    break;
                case "int":
                    declaredField.set(t,Integer.valueOf(value));
                    break;
                case "boolean":
                    declaredField.set(t,Boolean.valueOf(value));
                    break;
                default:
                    logger.info("实体类未处理的数据类型！");
            }
        } catch (NoSuchFieldException e) {
            StringBuilder sb = new StringBuilder();
            logger.info(sb.append("找不到==").append(field).append("===字段...!").toString(),e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static String getCellValue(Cell cell){
        Object cellValue = null;
        switch (cell.getCellType()){
            case NUMERIC: //数字
                cellValue = cell.getNumericCellValue();
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: //公式
                cellValue = cell.getCellFormula();
                break;
            case BLANK: //空值
                cellValue = "";
                break;
            case ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return String.valueOf(cellValue);
    }

    /**
     *  导出所用的workBook对象,实体对应的字段是第二个，表头显示内容是第一个
     *
     * @param sheetname sheet名称
     * @param title 表头对应实体字段
     * @param content 内容
     * @param <T> 实体类
     * @return
     */
    public static <T> XSSFWorkbook getWorkbook (String sheetname, String title, List<T> content) {
        //新建文档实例
        XSSFWorkbook workbook = new XSSFWorkbook();
        //在文档中添加表单
        XSSFSheet sheet = workbook.createSheet(sheetname);
        //创建单元格格式，并设置居中
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        // 标题栏
        XSSFRow titleRow = sheet.createRow(0);
        String[] titles = title.split(";");
        for (int i = 0; i < titles.length; i++) {
            String[] split = titles[i].split(",");
            XSSFCell cell = titleRow.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(split[0]);
        }

        // 内容栏
        for (int i = 0; i < content.size(); i++) {
            XSSFRow contentRow = sheet.createRow(i + 1);
            for (int j = 0; j < titles.length; j++) {
                XSSFCell cell = contentRow.createCell(j);
                String[] split = titles[j].split(",");
                if (split.length == 1){
                    continue; // 没用写对应字段就跳过，设定为不读取
                }
                cell.setCellStyle(style);
                cell.setCellValue(String.valueOf(getFieldValue(split[1], content.get(i))));

            }
        }

        return workbook;
    }

    public static <T> Object getFieldValue(String field, T t) {
        Field field1 = null;
        try {
            field1 = t.getClass().getDeclaredField(field);
            field1.setAccessible(true);
            return field1.get(t);
        } catch (NoSuchFieldException e) {
            StringBuilder sb = new StringBuilder();
            logger.info(sb.append("找不到==").append(field).append("===字段...!").toString(),e.getMessage());
            return "";
        } catch (IllegalAccessException e) {
            e.getMessage();
        }
        return "";
    }

    public XSSFCellStyle getStyle(Workbook workbook){
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}