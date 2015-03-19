package com.read.test.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yfwangrui on 2015/2/15.
 */
public class LockTest extends Thread {

    private final ReentrantLock lock;
    private final Condition condition;
    private final int index;

    private final static String[] words = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private AtomicInteger i;   //���ﲻ����Integer����ΪInteger��final�ģ��൱��ÿ���̵߳�i���ǵ����ģ���������ʵ��ͬ��

    public LockTest(ReentrantLock lock, Condition condition, AtomicInteger i, int index) {
        this.lock = lock;
        this.condition = condition;
        this.i = i;
        this.index = index;
    }

    public void run() {
        for (String word : words) {
            lock.lock();
            try {
                try {
                    while (i.get() % 3 != index)
                        condition.await();
                } catch (InterruptedException e) {
                    condition.signalAll();
                }
                System.out.println(word);
                i.getAndIncrement();
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
