package com.read.test.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by yfwangrui on 2014/11/10.
 */
public class ZookeeperMain {

    public static void main(String[] args) {

        try {
            TestCallback callback = new TestCallback();

            ZooKeeper zk = new ZooKeeper("localhost:2181,localhost:2182,localhost:2183", 50000, callback);
            callback.setZk(zk);
            callback.setzNode("/root");

            while (true) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
