package com.read.test.thread;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-6-18
 * Time: ����8:17
 * To change this template use File | Settings | File Templates.
 */

/**
 * ���룺����������
 * ��ֵ����ֵΪA����ʾӦ����A�߳���ִ�У�B,C�̵߳ȴ�
 * ��ֵΪB,Cͬ��
 */
class LockCode {
    /**
     * ��ǰ������ֵ����ʼ��ֵΪA����ʾ�����A�߳�����
     */
    private char code = 'A';

    /**
     * ����ģʽ
     */
    private LockCode() {
    }

    public static LockCode newInstance() {
        return new LockCode();
    }

    /**
     * ѭ����������
     * ÿһ�ε��ã����밴��A-B-C-A-...-��˳��ѭ������
     */
    public void setCode() {
        this.code = (char) (this.code + 1);
        if (this.code == 'D')
            this.code = 'A';
    }

    /**
     * �õ�����
     */
    public char getCode() {
        return this.code;
    }
}

/**
 * ��ɴ�ӡ�������߳���
 */
class PrintRunnable implements Runnable {
    /**
     * ��Ҫ��ӡ���ַ�
     */
    private char character = '?';
    /**
     * ��������
     */
    private final LockCode lockCode;

    PrintRunnable(char c, LockCode l) {
        this.character = c;
        this.lockCode = l;
    }

    /**
     * �߳�ִ��
     */
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lockCode) {//�߳�ͬ����������
                try {
                    //�����ǰ���е��̲߳������ڵ�ǰ�������ֵ������̵߳ȴ�
                    //���統ǰ�����߳���A��������ֵΪB����A�̵߳ȴ���
                    while (lockCode.getCode() != this.character)
                        lockCode.wait();
                    //��ֵƥ��ɹ�����ӡ�ַ�
                    System.out.print(this.character);
                    //������ֵ������һ���߳̿�������
                    lockCode.setCode();
                    //���������еȴ��̼߳���
                    lockCode.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

/**
 * ����
 */
public class ThreadLoopPrint {

    public static void main(String[] args) {
        LockCode lockCode = LockCode.newInstance();//��������
        Thread ta = new Thread(new PrintRunnable('A', lockCode));
        Thread tb = new Thread(new PrintRunnable('B', lockCode));
        Thread tc = new Thread(new PrintRunnable('C', lockCode));
        ta.start();
        tb.start();
        tc.start();
    }
}

