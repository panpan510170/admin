package com.pan.test.algorithm;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 按照百分比计算概率
 * 按几率产生随机数
 * Integer--
 *          抽取数据
 * @author pan
 * @date 2019/6/5 11:42
 */
public class RateRandomIntegerNumber {
    /**
     * 产生随机数
     * @param min 最小值
     * @param max 最大值
     * @return 随机结果
     */
    public static double produceRandomNumber(int min,int max){
        return RandomUtils.nextInt(min,max); //[min,max]
    }

    /**
     * 按比率产生随机数
     * @param min 最小值
     * @param max 最大值
     * @param separates 分割值（中间插入数）
     * @param percents 每段数值的占比（几率）
     * @return 按比率随机结果
     */
    public static double produceRateRandomNumber(int min,int max,List<Integer> separates,List<Integer> percents){
        if(min > max){
            throw new IllegalArgumentException("min值必须小于max值");
        }
        if(separates == null || percents==null || separates.size()==0){
            return produceRandomNumber(min,max);
        }
        if(separates.size() +1 != percents.size()){
            throw new IllegalArgumentException("分割数字的个数加1必须等于百分比个数");
        }
        int totalPercent = 0;
        for(Integer p:percents){
            if(p<0 || p>100){
                throw  new IllegalArgumentException("百分比必须在[0,100]之间");
            }
            totalPercent += p;
        }
        if(totalPercent != 100){
            throw new IllegalArgumentException("百分比之和必须为100");
        }
        for(double s:separates){
            if(s <= min || s >= max){
                throw new IllegalArgumentException("分割数值必须在(min,max)之间");
            }
        }
        int rangeCount = separates.size()+1; //例如：3个插值，可以将一个数值范围分割成4段
        //构造分割的n段范围
        List<Range> ranges = new ArrayList<Range>();
        int scopeMax = 0;
        for(int i=0;i<rangeCount;i++){
            Range range = new Range();
            range.min = (i==0 ? min:separates.get(i-1));
            range.max = (i== rangeCount-1 ?max:separates.get(i));
            range.percent = percents.get(i);

            //片段占比，转换为[1,100]区间的数字
            range.percentScopeMin = scopeMax +1;
            range.percentScopeMax = range.percentScopeMin + (range.percent-1);
            scopeMax = range.percentScopeMax;

            ranges.add(range);
        }
        //结果赋初值
        double r = min;
        int randomInt = RandomUtils.nextInt(1,101); //[1,100]
        for(int i=0;i<ranges.size();i++){
            Range range = ranges.get(i);
            //判断使用哪个range产生最终的随机数
            if(range.percentScopeMin <= randomInt && randomInt <= range.percentScopeMax){
                r = produceRandomNumber(range.min,range.max);
                break;
            }
        }
        return r;
    }

    public static class Range{
        public int min;
        public int max;
        public int percent; //百分比

        public int percentScopeMin; //百分比转换为[1,100]的数字的最小值
        public int percentScopeMax; //百分比转换为[1,100]的数字的最大值
    }

    public static void main(String[] args) {
        int one = 0;
        int two = 0;
        int three = 0;
        int foue = 0;
        int five = 0;
        int six = 0;

        List<Integer> separates1 = new ArrayList<Integer>();
        separates1.add(20);
        separates1.add(40);
        separates1.add(60);
        separates1.add(80);
        List<Integer> percents1 = new ArrayList<Integer>();
        percents1.add(30);
        percents1.add(25);
        percents1.add(20);
        percents1.add(15);
        percents1.add(10);
        for(int i=0;i<100;i++) {
            double number = produceRateRandomNumber(1, 100, separates1, percents1);
            if(number<=20){
                one ++;
            }
            if(number>20 && number<=40){
                two ++;
            }
            if(number>40 && number<=60){
                three ++;
            }
            if(number>60 && number<=80){
                foue ++;
            }
            if(number>80 && number<=100){
                five ++;
            }
            System.out.println("第二轮第"+i+"次"+String.format("%.2f",number));
        }
        System.out.println("one==="+one);
        System.out.println("two==="+two);
        System.out.println("three==="+three);
        System.out.println("foue==="+foue);
        System.out.println("five==="+five);
        System.out.println("six==="+six);
    }
}
