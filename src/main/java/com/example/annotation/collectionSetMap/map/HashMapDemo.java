package com.example.annotation.collectionSetMap.map;

import org.springframework.data.repository.cdi.Eager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> sSHashMap = new HashMap<>();
        Map<String, String> stringStringMap = Collections.synchronizedMap(sSHashMap); //这样HashMap就是能现场安全了

        sSHashMap.put("a", "a");
        sSHashMap.put("a","sas");
        HashMap<String, String> stringStringHashMap = new HashMap<>(2^10); // 设定初始容量
        stringStringHashMap.size();

        ConcurrentHashMap<String, String> conMap = new ConcurrentHashMap<>(2 ^ 6, (float) 0.75, 16);
        conMap.put("s","a");
        //BeanUtils.copyProperties();
        Set<Map.Entry<String, String>> entries = sSHashMap.entrySet();

        sSHashMap.forEach((K,V) -> {
           // System.out.println(K +" === "+ V);
        });

        ConcurrentHashMap<String, String> sSConcuurrent = new ConcurrentHashMap<>(); //也是线程安全

        // accessOrder ,true是访问顺序，false是增加顺序
        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<String, String>(2 ^ 6, (float)0.75, true);
        stringStringLinkedHashMap.put("a","aa");
        stringStringLinkedHashMap.put("b","bb");
        stringStringLinkedHashMap.put("c","cc");
        stringStringLinkedHashMap.put("d","dd");
        accessOne(stringStringLinkedHashMap);
        Set<String> strings = stringStringLinkedHashMap.keySet();

        stringStringLinkedHashMap.forEach((k,v) -> {
            System.out.println("stringStringLinkedHashMap: "+k+" === "+v);
        });
        //getSorted();

    }

    public static void accessOne(HashMap linked){
        System.out.println(linked.get("b"));
        System.out.println(linked.get("a"));
        System.out.println(linked.get("a"));
        System.out.println(linked.get("b"));
        System.out.println(linked.get("a"));
    }

    public static void getSorted(){

        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("a");
        hashSet.add("b");
        hashSet.add("c");
        hashSet.add("d");
        hashSet.add("e");
        hashSet.add("f");

        HashMap<String, String> hashMap = new HashMap<>(2^1);
        hashMap.put("a","aa");
        hashMap.put("b","bb");

        ConcurrentHashMap<String, String> conMap = new ConcurrentHashMap<String, String>(2 ^ 1, (float) 0.75, 1);
        conMap.put("a","aa");
        conMap.put("b","bb");
        conMap.put("c","bb");
        conMap.put("d","bb");
        conMap.put("e","bb");
        conMap.put("f","bb");
        conMap.put("g","bb");
        conMap.put("h","bb");
        conMap.put("i","bb");
        conMap.put("j","bb");
        conMap.put("k","bb");
        conMap.put("l","bb");

        for (int i = 0; i < strings.size(); i++) {
            System.out.println("strings: " + strings.get(i) + " === " + i);
        }

        final int[] m = {0};
        hashSet.forEach(s -> {
            System.out.println("hashSet"+s +" === "+ m[0]);
            m[0] += 1;
        });


        final int[] i = {0};
        hashMap.forEach((k,v) -> {
            System.out.println("HashMap: " + k + v + " ==== " + i[0]);
            i[0] = i[0] +1;
                });

        AtomicInteger j = new AtomicInteger();
        conMap.forEach((k,v) -> {
            System.out.println("conMap: " + k + v + " ==== " +j);
            j.set(j.get() + 1);
        });
    }
}
