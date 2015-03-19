package com.read.test.jdk.schedule;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yfwangrui on 2015/3/4.
 */
public class TimerTest {

//    @Test
    public static void main(String[] args) {
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task1 start at " + System.currentTimeMillis());
            }
        }, 0, 5000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task2 start at " + System.currentTimeMillis());
            }
        }, 0, 1000);
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
