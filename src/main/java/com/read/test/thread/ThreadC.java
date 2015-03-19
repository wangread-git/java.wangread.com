package com.read.test.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-6-18
 * Time: обнГ6:04
 * To change this template use File | Settings | File Templates.
 */
public class ThreadC implements Runnable{
    private AtomicInteger index;

    public ThreadC(AtomicInteger index) {
        this.index = index;
    }
    //    public ThreadB(PrintThread printThread) {
//        this.printThread = printThread;
//    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            while ((index.get() % 3) != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("c");
            index.incrementAndGet();
            System.out.print(index);
            notifyAll();
        }
    }
}
