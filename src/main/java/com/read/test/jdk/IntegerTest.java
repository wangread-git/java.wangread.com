package com.read.test.jdk;

import org.junit.Test;

import java.util.Properties;

/**
 * Created by yfwangrui on 2014/11/18.
 */
public class IntegerTest {

    static {
        Properties properties = System.getProperties();
        properties.put("num", 1111);
        System.setProperties(properties);
    }

    @Test
    public void test() {

        System.out.println(System.getProperties().getProperty("num"));
        System.out.println(Integer.getInteger("num"));
    }
}
