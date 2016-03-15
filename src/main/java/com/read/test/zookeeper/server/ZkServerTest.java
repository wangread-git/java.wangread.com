package com.read.test.zookeeper.server;

import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerMain;
import org.junit.Test;

/**
 * Created by wang.read on 2016/2/6.
 *
 * zookeeper server bootstrap
 */
public class ZkServerTest {

    private final static String CONFIG_PATH = "D:\\Program Files\\zookeeper\\server1\\zookeeper-3.4.6\\conf\\zoo.cfg";

    @Test
    public void startSingle() {
        ZooKeeperServerMain.main(new String[]{CONFIG_PATH});
    }

    @Test
    public void startQuorum() {
        QuorumPeerMain.main(new String[]{CONFIG_PATH});
    }
}
