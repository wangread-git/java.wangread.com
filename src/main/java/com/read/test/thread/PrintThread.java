package com.read.test.thread;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-5-21
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class PrintThread {
    private boolean flag;
    private final static String[] a = {"a", "b", "c", "d", "e", "f"};
    private final static Integer[] b = {1, 2, 3, 4, 5, 6};
    private int index;

    public synchronized void printA() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(a[index]);
        flag = true;
        notify();
    }

    public synchronized void printB() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(b[index]);
        flag = false;
        index++;
        notify();
    }

    public int sizeA() {
        return a.length;
    }

    public int sizeB() {
        return b.length;
    }
}
