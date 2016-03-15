package com.read.test;

import org.junit.Test;

/**
 * Created by yfwangrui on 2014/12/10.
 */
public class TestMain {

    @Test
    public void test() {
//        Properties prop = new Properties();
//        prop.put("zookeeper.clientCnxnSocket", "ClientCnxnSocketNIO");
//        System.setProperties(prop);
//        System.out.println(System.getProperty("zookeeper.clientCnxnSocket"));

        String str = "sb,";
        String[] s = str.split(",");
        System.out.println(s.length);
    }
}
