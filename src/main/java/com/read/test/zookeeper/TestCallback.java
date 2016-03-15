package com.read.test.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * Created by yfwangrui on 2014/11/12.
 */
public class TestCallback implements Watcher, AsyncCallback.DataCallback {

    private ZooKeeper zk;
    private String zNode;

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void setzNode(String zNode) {
        this.zNode = zNode;
    }

    public void process(WatchedEvent event) {
        System.out.println(event.getType());
        if (event.getType() == Event.EventType.NodeDataChanged) {
            zk.getData(zNode, this, this, null);
        }
    }

    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println(rc);
        String value = new String(data);
        System.out.println(value);
    }
}
