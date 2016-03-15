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

            ZooKeeper zk = new ZooKeeper("192.168.23.3:2181,192.168.23.3:2182,192.168.23.3:2183", 500000, callback);
            callback.setZk(zk);
            callback.setzNode("/root");
            zk.getData("/root", callback, callback, null);
            while (true) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
