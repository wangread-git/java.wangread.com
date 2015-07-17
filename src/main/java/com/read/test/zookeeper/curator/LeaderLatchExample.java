package com.read.test.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by yfwangrui on 2015/7/15.
 *
 * leader latch example
 *
 * The difference between leader latch and leader election is that,
 * when listener of a leader elected by leader election is done, another
 * client will be the new leader, but the leader elected by leader latch
 * will keep the leadership until the client is over. so, we can use leader
 * latch to start quartz for single worker.
 */
public class LeaderLatchExample {

    private final static String connectionStr = "localhost:2181,localhost:2182,localhost:2183";
    private final static String root = "/leader/latch";

    @Test
    public void test() {
        CuratorFramework client = null;
        LeaderLatch latch = null;
        try {
            client = CuratorFrameworkFactory.newClient(connectionStr, new ExponentialBackoffRetry(1000, 3));
            client.start();
            latch = new LeaderLatch(client, root);
            latch.addListener(new LeaderLatchListener() {
                public void isLeader() {
                    System.out.println("i am leader");
                    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-quartz.xml");
                    Scheduler scheduler = (Scheduler) context.getBean("scheduler");
                    try {
                        scheduler.start();
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }

                public void notLeader() {

                }
            });
            Thread.sleep(10 * 1000);
            System.out.println("start...");
            latch.start();
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (latch != null) {
                try {
                    latch.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                client.close();
            }
        }
    }
}
