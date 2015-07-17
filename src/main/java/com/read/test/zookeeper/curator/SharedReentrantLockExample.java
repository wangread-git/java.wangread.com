package com.read.test.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Test;

/**
 * Created by yfwangrui on 2015/7/15.
 * <p/>
 * shared reentrant lock example
 */
public class SharedReentrantLockExample {

    private final static String connectionStr = "localhost:2181,localhost:2182,localhost:2183";
    private final static String root = "/lock/reentrant";

    @Test
    public void test() {
        CuratorFramework client = null;
        InterProcessMutex mutex = null;
        try {
            client = CuratorFrameworkFactory.newClient(connectionStr, new ExponentialBackoffRetry(1000, 3));
            mutex = new InterProcessMutex(client, root);
            client.start();
            mutex.acquire();
            int i = 10;
            while (i >= 0) {
                System.out.println(System.currentTimeMillis());
                Thread.sleep(1000 * 5);
                i--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mutex != null) {
                try {
                    mutex.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            CloseableUtils.closeQuietly(client);
        }
    }
}
