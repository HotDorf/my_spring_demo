package com.example.annotation.utils;

import com.example.annotation.poi.Person;
import com.example.annotation.springDataJPA.entity.User;
import com.example.annotation.utils.Entity.Animal;
import org.apache.commons.lang3.time.DateUtils;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ValueRange;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class DateOperate {

    public static void main(String[] args) {
     //   dateSimple();
     //   testDefaultNull();
     //   collections();
        initDate();
    }

    private static void initDate() {
        String format = getDateSimple();
        Date date = null;
        try {
            date = DateUtils.parseDate(format, "yyyy-MM-dd HH:mm:ss.SSS");
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.set(Calendar.HOUR_OF_DAY,0);
            instance.set(Calendar.MINUTE,0);
            instance.set(Calendar.SECOND,0);
            instance.set(Calendar.MILLISECOND,0);
            Date time = instance.getTime();

            getDateSimple(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static String getDateSimple(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        return format;
    }

    private static String getDateSimple(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);
        return format;
    }

    public static void dateSimple(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);
    }

    public static void testDefaultNull(){
        Person person = new Person();
        System.out.println(person.getName());
        System.out.println(person.getAge());
    }

    public static void collections(){
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("zhangsan","houzi"));
        animals.add(new Animal("lisi","houzi"));
        animals.add(new Animal("wangwu","houzi"));
        animals.add(new Animal("zhangsan","houzi"));
        animals.add(new Animal("zhangsan","houzi"));

        List<Animal> collect =animals.stream().
                collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Animal::getName))), ArrayList::new));
        collect.forEach(animal -> { System.out.println(animal); });
    }
       // List<User> collect = resDTO.getUserList().stream().
         //       collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(UserDTO::getUserName))), ArrayList::new));
}

