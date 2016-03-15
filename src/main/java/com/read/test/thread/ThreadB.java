package com.read.test.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-5-21
 * Time: 下午5:13
 * To change this template use File | Settings | File Templates.
 */
public class ThreadB implements Runnable {
    //    private PrintThread printThread;
    private AtomicInteger index;

    public ThreadB(AtomicInteger index) {
        this.index = index;
    }
    //    public ThreadB(PrintThread printThread) {
//        this.printThread = printThread;
//    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            while ((index.get() % 3) != 2) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("b");
            index.incrementAndGet();
            System.out.print(index);
            notifyAll();
        }
    }
}
