package com.read.test.thread;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-5-21
 * Time: 下午4:40
 * To change this template use File | Settings | File Templates.
 */
public class ThreadA implements Runnable {
    private final Lock lock;
    private int name;

    public ThreadA(Lock lock, int name) {
        this.lock = lock;
        this.name = name;
    }

    public void run() {

        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (name != lock.getIndex() % 3) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(name);
                lock.next();
                lock.notifyAll();
                //此时所有线程都被唤醒，但是只有当前这个线程走完后面流程，释放了锁才能继续后面的工作
            }
        }
    }
}

class Lock {
    private int index;

    Lock(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void next() {
        index++;
        if (index == 3) {
            index = 0;
        }
    }
}
