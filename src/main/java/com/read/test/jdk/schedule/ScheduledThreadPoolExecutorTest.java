package com.read.test.jdk.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yfwangrui on 2015/3/4.
 */
public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println("task" + finalI + " execute at " + System.currentTimeMillis());
                }
            }, 1000, 5000, TimeUnit.MILLISECONDS);
        }
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
