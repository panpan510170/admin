package com.mshd.admin;

import com.mshd.vo.mathes.Apple;
import com.mshd.vo.mathes.Jisun;
import com.mshd.vo.user.UserRegistVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.internal.util.collections.ListUtil.filter;

/**
 * Created by Pangaofeng on 2018/9/25
 *
 * Lomdba 表达式 stream流
 *
 * 一些简单的使用
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LomdbaTest {

    /**
     * LomdbaTest  表达式 stream流
     *
     * 遍历List
     * */
    @Test
    public void forList() {
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

        //lambda
        //Output : A,B,C,D,E
        items.forEach(item->System.out.println(item));

        //Output : C
        items.forEach(item->{
            if("A".equals(item)){
                System.out.println("123");
            }
        });

        //method reference
        //Output : A,B,C,D,E
        items.forEach(System.out::println);

        //Stream and filter
        //Output : B
        items.stream()
                .filter(s->s.contains("B"))
                .forEach(System.out::println);
    }


    /**
     * LomdbaTest  表达式 stream流
     *
     * 操纵List
     * */
    @Test
    public void testList() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        // 开头为J
        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> ((String) str).startsWith("J"));

        // 结尾为 a
        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> ((String) str).endsWith("a"));

        // true
        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        // false
        System.out.println("Print no language : ");
        languages = filter(languages, (str) -> false);

        // 长度大于4
        System.out.println("Print language whose length greater than 4:");
        languages = filter(languages, (str) -> ((String) str).length() > 4);
        System.out.println(languages);


        List<Integer> myList = new ArrayList<>();
        for(int i=0; i<100; i++) myList.add(i);

        //有序流
        Stream<Integer> sequentialStream = myList.stream();

        //并行流
        Stream<Integer> parallelStream = myList.parallelStream();

        //使用lambda表达式，过滤大于90的数字
        Stream<Integer> highNums = parallelStream.filter(p -> p > 80);

        //lambdag表达式 forEach循环
        highNums.forEach(p -> System.out.println("最大数 并行="+p));

        Stream<Integer> highNumsSeq = sequentialStream.filter(p -> p > 80);

        highNumsSeq.forEach(p -> System.out.println("最大数 有序="+p));

    }

    /**
     * LomdbaTest  表达式 stream流
     *
     * 遍历map
     * */
    @Test
    public void forMap() {
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);

        System.out.println("普通 ******************************************************************");

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }


        System.out.println("forEach + lambda 表达式 ******************************************************************");

        items.forEach((k,v)->{
            System.out.println("Item : " + k + " Count : " + v);
            if("E".equals(k)){
                System.out.println("Hello E");
            }
        });
    }

    /**
     * LomdbaTest  表达式 stream流
     *
     * map转换list
     * */
    @Test
    public void MapToList() {
        Map<String, String> items = new HashMap<>();
        items.put("A", "10");
        items.put("B", "20");
        items.put("C", "30");
        items.put("D", "40");
        items.put("E", "50");
        items.put("F", "60");

        List<UserRegistVO> list = items.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey()))
                .map(e -> new UserRegistVO(e.getKey(), e.getValue())).collect(Collectors.toList());

        System.out.println(list);

        List<UserRegistVO> list1 = items.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .map(e -> new UserRegistVO(e.getKey(), e.getValue())).collect(Collectors.toList());


        System.out.println(list1);

        List<UserRegistVO> list2 = items.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map(e -> new UserRegistVO(e.getKey(), e.getValue())).collect(Collectors.toList());

        System.out.println(list2);
    }

    /**
     * LomdbaTest  表达式
     *
     * 学习lomdba
     * */
    @Test
    public void learnLomdba() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        new Thread(() -> System.out.println("In Java8, Lambda expression !!") ).start();

    }

    @Test
    public void testQiuHe(){
        List<BigDecimal> costBeforeTax = new ArrayList<>();
        costBeforeTax.add(new BigDecimal("100"));
        costBeforeTax.add(new BigDecimal("200"));
        costBeforeTax.add(new BigDecimal("300"));
        costBeforeTax.add(new BigDecimal("400"));
        costBeforeTax.add(new BigDecimal("500"));
        //BigDecimal total = BigDecimal.ZERO;
        /*for (BigDecimal cost : costBeforeTax) {
            total = total.add(cost);

        }*/
        //System.out.println("Total : " + total);
        // New way:
        BigDecimal bill = costBeforeTax.stream().map((cost) -> cost.add(cost))
                .reduce((sum, cost) -> sum.add(cost))
                .get();

        System.out.println("Total : " + bill);
    }

    @Test
    public void testJisuan(){
        List<Integer> costBeforeTax = new ArrayList<>();
        costBeforeTax.add(1);
        costBeforeTax.add(2);
        costBeforeTax.add(3);
        costBeforeTax.add(4);
        costBeforeTax.add(5);
        IntSummaryStatistics stats = costBeforeTax.stream().mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());

    }

    @Test
    public void testQiuHeObject(){
       /* List<Jisun> costBeforeTax = new ArrayList<>();
        Jisun Jisun = new Jisun();
        Jisun.setId(1L);
        Jisun.setA(new BigDecimal("1"));
        Jisun jisun1 = new Jisun();
        jisun1.setId(2L);
        jisun1.setA(new BigDecimal("2"));
        Jisun jisun2 = new Jisun();
        jisun2.setId(3L);
        jisun2.setA(new BigDecimal("3"));
        Jisun jisun3 = new Jisun();
        jisun3.setId(4L);
        jisun3.setA(new BigDecimal("4"));
        Jisun jisun4 = new Jisun();
        jisun4.setId(5L);
        jisun4.setA(new BigDecimal("5"));

        costBeforeTax.add(jisun4);
        costBeforeTax.add(jisun3);
        costBeforeTax.add(jisun2);
        costBeforeTax.add(jisun1);
        costBeforeTax.add(Jisun);*/

        List<Apple> appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);

        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("totalMoney:"+totalMoney); //totalMoney:17.48

        /*BigDecimal bill = costBeforeTax.stream()
                .reduce((BigDecimal sum, BigDecimal Jisun.getA()) -> sum.add((Jisun.getA())
                .get();*/

        //System.out.println("Total : " + bill);
    }

}
