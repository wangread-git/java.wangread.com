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
            //ClientCnxn里的sendThread和eventThread为守护线程，如果main方法结束的话，这俩线程也就结束了，
            //这里加上while循环，防止这俩线程提前结束
            while (true) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
