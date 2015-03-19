package com.read.test.thread;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yfwangrui on 2015/2/9.
 */
public class ThreadLocalTest {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    public static Map<String, Object> get() {
        return threadLocal.get();
    }

    public static void set(Map<String, Object> map) {
        threadLocal.set(map);
    }

    @Test
    public void test() {
        Thread thread1A = new Thread(new Thread1());
        Thread thread1B = new Thread(new Thread1());
        Thread thread2A = new Thread(new Thread2());
        Thread thread2B = new Thread(new Thread2());
        thread1A.start();
        thread1B.start();
        thread2A.start();
        thread2B.start();
        System.out.println(ThreadLocalTest.get());
    }
}

class Thread1 implements Runnable {
    public void run() {
        Map<String, Object> map = ThreadLocalTest.get();
        map.put("name", "thread1");
        map.put("id", Thread.currentThread().getId());
        ThreadLocalTest.set(map);
    }
}

class Thread2 implements Runnable {
    public void run() {
        Map<String, Object> map = ThreadLocalTest.get();
        map.put("name", "thread2");
        map.put("id", Thread.currentThread().getId());
        ThreadLocalTest.set(map);
    }
}
