package com.geek45.commons.random;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil implements Serializable {
    private static final long serialVersionUID = 1l;
    private static ThreadLocalRandom tlRandom = null;
    public static void main(String[] args) {
        System.err.println(getRandom(1, 3));
        System.err.println(getRandomStr(6));
        int t = Integer.valueOf(getRandomStr(6));
        System.err.println(t);
        boolean s = getRandomBoolean();
        System.err.println(s);
    }
    private static class SingleInner{
        private static ThreadLocalRandom tlRandom = ThreadLocalRandom.current();
    }
    //获取布尔类型的随机值
    private static boolean getRandomBoolean(){
        tlRandom = initRandom();
        return tlRandom.nextBoolean();
    }
    //获取指定区间的随机数字符串
    public static String getRandomStr(long start, long end) {
        long random = getRandom(start, end);
        return String.valueOf(random);
    }
    //获取指定位数的随机数字符串
    public static String getRandomStr(long randomSize) {
        long start = 1;
        for (int i = 1; i < randomSize; i++) {
            start *= 10;
        }
        long end = start * 10;
        return String.valueOf(getRandom(start, end));
    }
    public static long getRandom(long start, long end) {
        tlRandom = initRandom();
        return tlRandom.nextLong(start,end);
    }

    public static int getRandomInt(int start, int end) {
        tlRandom = initRandom();
        return (int)tlRandom.nextLong(start,end);
    }
    public static int nextInt(int end) {
        tlRandom = initRandom();
        return (int)tlRandom.nextLong(0,end);
    }
    private static ThreadLocalRandom initRandom(){
        if (tlRandom == null) {
            return SingleInner.tlRandom;
        }
        return tlRandom;
    }
}
