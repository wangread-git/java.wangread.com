package com.read.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yfwangrui on 2014/12/10.
 */
public class TestMain {

    private final static Logger LOG = LoggerFactory.getLogger(TestMain.class);

    @Test
    public void test() {
        String str1 = "abc";
        String str2 = new String(str1.getBytes());
        String str3 = new String(str1.getBytes()).intern();
        System.out.println((str1 == str2) + ", " + (str2 == str3));
    }
}
