package com.rayton.gps;


import com.rayton.gps.dao.common.DeviceStatus;
import com.rayton.gps.dao.common.DeviceStatusDto;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TheTest {

    public static void ccc(String args[]) {


        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        strings.forEach(s -> System.out.println(s.isEmpty()));


        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println(String.format("空字符串数量为: %s", count));
        strings.stream().filter(string -> string.isEmpty()).forEach(s -> System.out.println(s));


        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        // List<String>  squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        // System.out.println("Squares List: " + squaresList);
        // System.out.println("列表: " + integers);
        //
        // IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();
        //
        // System.out.println("列表中最大的数 : " + stats.getMax());
        // System.out.println("列表中最小的数 : " + stats.getMin());
        // System.out.println("所有数之和 : " + stats.getSum());
        // System.out.println("平均数 : " + stats.getAverage());
        // System.out.println("随机数: ");
        //
        // random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);
    }

    public static void main(String args[]) {
        DeviceStatusDto dto = new DeviceStatusDto();
        DeviceStatus status = new DeviceStatus();
        dto.number = "dddd";
        BeanUtils.copyProperties(dto, status);
        System.out.println(status);
    }

}

