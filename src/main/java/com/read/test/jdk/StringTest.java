package com.read.test.jdk;

import org.junit.Test;

/**
 * Created by yfwangrui on 2014/11/18.
 */
public class StringTest {

    @Test
    public void test() {
        String str = "aaa";
        String str1 = String.valueOf("aaa");
        String str2 = new String("aaa");
        System.out.println(str == str1);
        String str3 = str2.intern();
        System.out.println(str3 == str2);
        System.out.println(str3 == str);
        if (Boolean.FALSE) {}
    }
}
