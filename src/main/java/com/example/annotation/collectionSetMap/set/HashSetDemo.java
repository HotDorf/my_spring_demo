package com.example.annotation.collectionSetMap.set;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.RandomAccess;

public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<String> strHashSet = new HashSet<>();
        Object o = new Object();
        //strHashSet.add("1");
        //strHashSet.add("1");
        String str = "1";
        String str2 = "1";
        String s = new String("adsfda");
        String s1 = new String("dsasaddas");
        strHashSet.add(s);
        strHashSet.add(s1);

        LinkedList<String> stringsLinked = new LinkedList<>();
        stringsLinked.add("a");
        stringsLinked.add("b");
        String s2 = stringsLinked.peekFirst(); //窥探第一个值，返回第一个值
        System.out.println(s2);
        String s3 = stringsLinked.pollFirst(); // 删除第一个值并返回删除的值
        System.out.println(s3);
        stringsLinked.forEach(each -> {
            System.out.println(each);
            System.out.println(each.hashCode());
        });

        strHashSet.forEach(each -> {
            System.out.println(each);
            System.out.println(each.hashCode());
                });
    }

}
