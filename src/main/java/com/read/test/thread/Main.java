package com.read.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-5-21
 * Time: ÏÂÎç5:14
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] argv) {
//        PrintThread printThread = new PrintThread();
//        Thread threadA = new Thread(new ThreadA(printThread));
//        Thread threadB = new Thread(new ThreadB(printThread));
//        threadA.start();
//        threadB.start();
//        ExecutorService exec = Executors.newFixedThreadPool(5);
//        int sum = 0;
//        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exec);
//        long begin = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            Callable<Integer> task = new Callable<Integer>() {
//                public Integer call() {
//                    int j;
//                    for (j = 0; j < 100000; j++) {
//                    }
//                    return j;
//                }
//            };
//            completionService.submit(task);
//        }
//        for (int i = 0; i< 1000; i++) {
//            try {
//                Future<Integer> future = completionService.take();
//                sum += future.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - begin);
//        System.out.println(sum);
//        sum = 0;
//        for(int k = 0; k < 100000000; k++) {
//            sum++;
//        }
//        long end2 = System.currentTimeMillis();
//        System.out.println(end2 - end);
//        System.out.println(sum);
//        exec.shutdown();
//        Lock lock = new Lock(1);
//        Thread a = new Thread(new ThreadA(lock, 1));
//        Thread b = new Thread(new ThreadA(lock, 2));
//        Thread c = new Thread(new ThreadA(lock, 0));
//        a.start();
//        b.start();
//        c.start();

//        ReentrantLock lock = new ReentrantLock();
//        Condition condition = lock.newCondition();
//        AtomicInteger i = new AtomicInteger(0);
//        LockTest test0 = new LockTest(lock, condition, i, 0);
//        LockTest test1 = new LockTest(lock, condition, i, 1);
//        LockTest test2 = new LockTest(lock, condition, i, 2);
//
//        test0.start();
//        test1.start();
//        test2.start();

        FutureTask futureTask = new FutureTask(new Callable() {
            private int i;
            public Object call() throws Exception {
                return ++i;
            }
        });
        Thread thread1 = new Thread(futureTask);
        Thread thread2 = new Thread(futureTask);
        thread1.start();
        thread2.start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
