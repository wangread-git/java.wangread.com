package com.read.test.pattern.singleton;

/**
 * Created by bjyfwangrui on 2016/8/4.
 * <p>
 * singleton demo
 * 线程安全保证
 * 1、执行1时(getstatic)，SingletonHolder类被初始化，且只初始化一次，故2只被执行1次，不会发生多个线程写instance引用的情况
 * 2、final保证了instance的可见性，即不同的线程取到的instance是同一份，不会出现某个线程取到的instance为null的情况
 */
public class Singleton {
    private Singleton() {
    }

    private static class SingletonHolder {
        private final static Singleton instance = new Singleton();//2
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;//1
    }
}