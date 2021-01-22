package com.example.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class GcLogAnalysis {

    private static Random random = new Random();

    public static void main1(String[] args) {

        long startTime = System.currentTimeMillis();

        long l = TimeUnit.SECONDS.toMillis(1);

        long endTime = startTime +l;

        LongAdder counter = new LongAdder();

        System.out.println("正在执行........");

        int cacheSize = 2000;
        Object[] cacheGarbage = new Object[cacheSize];
        while(System.currentTimeMillis()<endTime){
            Object garbage = generateCacheGarbage(100 * 1024);
            counter.increment();
            int randomIndex = random.nextInt(2*cacheSize);
            if(randomIndex<cacheSize){
                cacheGarbage[randomIndex] =garbage;
            }
        }
        System.out.println("执行完毕,共生成对象数:===="+counter.longValue());
    }

    public static void main(String[] args) {
        String s1=new String("1");
        String s2 = "1";
        String s = s1.intern();
        boolean equals = s1.equals(s2);
        System.out.println(equals);
        System.out.println(s1==s2);
        System.out.println(s2==s);

    }


    private static Object generateCacheGarbage(int max){
        int randomSize = random.nextInt(max);
        int type = randomSize % 4;
        Object result = null;
        switch (type){
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder builder = new StringBuilder();
                String randomStr = "randomString-Anything";
                while(randomStr.length()<randomSize){
                    builder.append(randomStr);
                    builder.append(max);
                    builder.append(randomSize);
                }
                result=builder.toString();
                break;
        }
        return result;
    }


}
