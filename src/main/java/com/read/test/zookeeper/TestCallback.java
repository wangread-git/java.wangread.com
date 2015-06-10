package com.read.test.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * Created by yfwangrui on 2014/11/12.
 */
public class TestCallback implements Watcher, AsyncCallback.StatCallback {

    private ZooKeeper zk;
    private String zNode;

    public void processResult(int rc, String path, Object ctx, Stat stat) {
        System.out.println("keeper exception code:" + rc);
        switch (rc) {
            case 4:
                break;
            default:
                return;
        }

        byte b[];
        try {
            b = zk.getData(zNode, false, null);
            System.out.println("get data:" + new String(b));
        } catch (KeeperException e) {
            // We don't need to worry about recovering now. The watch
            // callbacks will kick off any exception handling
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void setzNode(String zNode) {
        this.zNode = zNode;
    }

    public void process(WatchedEvent event) {
//        zk.exists("/root", true, this, null);
        switch (event.getType()) {
            case None:
                break;
            case NodeDataChanged:
                break;
            default:
                return;
        }

        byte b[];
        try {
            b = zk.getData(zNode, true, null);
            System.out.println("get data:" + new String(b));
        } catch (KeeperException e) {
            // We don't need to worry about recovering now. The watch
            // callbacks will kick off any exception handling
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
