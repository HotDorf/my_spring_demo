package com.example.annotation.reflect;

import com.example.annotation.poi.FiveData;
import com.example.annotation.poi.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetFieldvalue {

    private static final Logger logger = LoggerFactory.getLogger(GetFieldvalue.class);

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Person person1 = new Person(1, "zhangsan", 18, "男");
        Person person2 = new Person(2, "lisi", 18, "男");
        FiveData fiveData = new FiveData(1, "s", true);
        //getFieldValue("number", person1);
        //getFieldValue(person1);
        //setFieldValue("number",person1);
        //System.out.println(person1);
        testType("name",person1);
        testType("number",person1);
        testType("isSingle",fiveData);



    }

    // 已知范型字段名，将int数据根据这个字段的数据类型，强转之后赋值给这个字段
    public static <T> void testType2Type(String field, T t,int num){

    }

    // 获取数据类型
    public static <T> void testType(String field, T t){
        String str = "s";
        //System.out.println(str.getClass().getTypeName());
        Field declaredField = null;
        try {
            declaredField = t.getClass().getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        declaredField.setAccessible(true);
        //System.out.println(declaredField.getType());
        System.out.println(declaredField.getGenericType().getTypeName());
    }

    public static <T> void setFieldValue(String field,T t){
        Field declaredField = null;
        try {
            declaredField = t.getClass().getDeclaredField(field);
            declaredField.setAccessible(true);
            declaredField.set(t,3);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     *获取该对象中的值
     *
     * @param t     实例对象
     * @param <T>
     * @return
     */
    public static <T> void getFieldValue(T t) throws IllegalAccessException {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true); // 暴力反射，克制private
            System.out.print(declaredField.getName() + "" + declaredField.get(t));
            System.out.println();
        }
    }

    // 由字段在实例对象中获取该字段的值
    public static <T> void getFieldValue(String filed,T t)   {
        Field number = null;
        try {
            number = t.getClass().getDeclaredField("number");
            number.setAccessible(true);
        } catch (NoSuchFieldException e) {
            logger.info("没有发现这个字段，field={}",filed);
        }
        System.out.println(number.getName());
        System.out.println(number.getType());
        try {
            System.out.println(number.get(t));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("==========================");
        //复杂版
       /* Field declaredField = null;
        try {
            declaredField = t.getClass().getDeclaredField(filed);
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println(declaredField.getName()+ "  " + filed);
        PropertyDescriptor pd = null;
        try {
            pd = new PropertyDescriptor(declaredField.getName(),t.getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        //获取get方法
        Method getMethod = pd.getReadMethod();
        Object valueObj = ReflectionUtils.invokeMethod(getMethod, t);
        System.out.println(valueObj);*/
    }

    // 获取对象中指定字段的值
    public static <T> void getFieldValue(String[] fields, T t) throws IllegalAccessException {
        for (String field : fields) {
            Field number = null;
            try {
                number = t.getClass().getDeclaredField("number");
            } catch (NoSuchFieldException e) {
                e.getMessage();
                // 没有这个字段
                continue;
            }
        }
    }

}

