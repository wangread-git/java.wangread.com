package com.read.test.thread;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-6-18
 * Time: 下午8:17
 * To change this template use File | Settings | File Templates.
 */

/**
 * 锁码：公共数据区
 * 码值：码值为A，表示应该由A线程来执行，B,C线程等待
 * 码值为B,C同理。
 */
class LockCode {
    /**
     * 当前锁码码值，初始码值为A，表示最初由A线程运行
     */
    private char code = 'A';

    /**
     * 单例模式
     */
    private LockCode() {
    }

    public static LockCode newInstance() {
        return new LockCode();
    }

    /**
     * 循环设置锁码
     * 每一次调用，锁码按照A-B-C-A-...-的顺序循环往复
     */
    public void setCode() {
        this.code = (char) (this.code + 1);
        if (this.code == 'D')
            this.code = 'A';
    }

    /**
     * 得到锁码
     */
    public char getCode() {
        return this.code;
    }
}

/**
 * 完成打印工作的线程类
 */
class PrintRunnable implements Runnable {
    /**
     * 需要打印的字符
     */
    private char character = '?';
    /**
     * 公共锁码
     */
    private final LockCode lockCode;

    PrintRunnable(char c, LockCode l) {
        this.character = c;
        this.lockCode = l;
    }

    /**
     * 线程执行
     */
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lockCode) {//线程同步操作锁码
                try {
                    //如果当前运行的线程并不等于当前锁码的码值，则改线程等待
                    //比如当前运行线程是A，但是码值为B，则A线程等待。
                    while (lockCode.getCode() != this.character)
                        lockCode.wait();
                    //码值匹配成功，打印字符
                    System.out.print(this.character);
                    //设置码值，让下一个线程可以运行
                    lockCode.setCode();
                    //让其他所有等待线程激活
                    lockCode.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

/**
 * 测试
 */
public class ThreadLoopPrint {

    public static void main(String[] args) {
        LockCode lockCode = LockCode.newInstance();//公共锁码
        Thread ta = new Thread(new PrintRunnable('A', lockCode));
        Thread tb = new Thread(new PrintRunnable('B', lockCode));
        Thread tc = new Thread(new PrintRunnable('C', lockCode));
        ta.start();
        tb.start();
        tc.start();
    }
}

