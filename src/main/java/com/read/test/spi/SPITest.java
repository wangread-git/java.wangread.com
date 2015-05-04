package com.read.test.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * Created by yfwangrui on 2015/4/3.
 */
public class SPITest {

    @Test
    public void test() {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        for (Search search : s) {
            search.search();
        }
    }
}
