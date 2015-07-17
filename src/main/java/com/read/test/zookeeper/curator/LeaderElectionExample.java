package com.read.test.zookeeper.curator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yfwangrui on 2015/7/15.
 *
 * leader election example
 */
public class LeaderElectionExample {

    private final static Log log = LogFactory.getLog(LeaderElectionExample.class);

    private final static String connectionStr = "localhost:2181,localhost:2182,localhost:2183";
    private final static String root = "/leader/election";

    @Test
    public void test() {
        CuratorFramework client = null;
        LeaderSelector selector = null;
        try {
            client = CuratorFrameworkFactory.newClient(connectionStr, new ExponentialBackoffRetry(1000, 3));
            client.start();
            selector = new LeaderSelector(client, root, new LeaderSelectorListener() {
                public void takeLeadership(CuratorFramework client) throws Exception {
                    System.out.println("i am leader");
                    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-quartz.xml");
                    Scheduler scheduler = (Scheduler) context.getBean("scheduler");
                    scheduler.start();
                    Thread.sleep(60 * 1000);
                }

                public void stateChanged(CuratorFramework client, ConnectionState newState) {

                }
            });
            Thread.sleep(30 * 1000);
            System.out.println("start...");
            selector.start();
            Thread.sleep(300 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (selector != null) {
                selector.close();
            }
            if (client != null) {
                client.close();
            }
        }
    }
}
