package com.example.annotation.springContextAweraTest;

import com.example.annotation.poi.Person;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        /*String str ="a";
        ArrayList<String> strings = new ArrayList<>();
        String s = "b";
        System.out.println(strings.hashCode());
        System.out.println(str.hashCode());
        System.out.println(s.hashCode());
        s = str;
        System.out.println(str.hashCode());
        System.out.println(s.hashCode());
        System.out.println(s);*/
       /* Object accountDao = SpringContextAweraTest.getBean("accountDao");
        if (accountDao == null) {
            System.out.println("null");
        }*/

        ArrayList<Person> people = new ArrayList<>();
        Person person = new Person();
        person.setSex("sad");
        Person person1 = new Person(1, "aa", 3, "as");
        people.add(person);
        people.add(person1);
        System.out.println(people.toString());

        String[] strs = {"s","s","saa"};

    }
}
