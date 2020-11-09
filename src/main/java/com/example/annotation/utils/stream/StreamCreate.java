package com.example.annotation.utils.stream;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreate {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // 从集合创建
        Stream<String> stream1 = list.stream();
        list.parallelStream();

        // 从数组创建
        //Arrays.stream(new int[] { 2, 3, 5 });
        int[] ints = {1, 2, 3};
        IntStream stream = Arrays.stream(ints);

        // 创建数字流
        IntStream intStream1 = IntStream.of(3, 3, 3);
        IntStream intStream = IntStream.rangeClosed(1, 10); //创建一个指定区域的int流
        System.out.println(intStream.map(StreamCreate::getInt).sum());
        //System.out.println(intStream.sum()); 流只能用一次，用完就没有了，上面用了一次后再用就异常了
        System.out.println(Arrays.toString(intStream1.map(StreamCreate::getInt).toArray()));

        // 使用random创建一个无限流
        IntStream limit = new Random().ints().limit(10);
        Random random = new Random();
        //System.out.println(limit.map(StreamCreate::getInt).max().getAsInt());
        System.out.println(Arrays.toString(limit.map(StreamCreate::getInt).toArray()));

        // 自己产生流 20个数
        Stream<Integer> limit1 = Stream.generate(random::nextInt).limit(20);
        System.out.println(Arrays.toString(limit1.map(StreamCreate::getInt).toArray()));


    }

    private static int getInt(int i){
        return i+=1;
    }
}

