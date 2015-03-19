package com.read.test;

import org.junit.Test;

import java.util.Properties;

/**
 * Created by yfwangrui on 2014/12/10.
 */
public class TestMain {

    @Test
    public void test() {
        Properties prop = new Properties();
        prop.put("zookeeper.clientCnxnSocket", "ClientCnxnSocketNIO");
        System.setProperties(prop);
        System.out.println(System.getProperty("zookeeper.clientCnxnSocket"));
    }
}
