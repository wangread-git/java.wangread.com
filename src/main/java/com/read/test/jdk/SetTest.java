package com.read.test.jdk;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yfwangrui on 2014/12/12.
 */
public class SetTest {

    @Test
    public void test() {
        Set<String> set = new HashSet<String>();
        for (String str : set) {
            System.out.println(str);
        }
    }
}
