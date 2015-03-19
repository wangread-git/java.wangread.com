package com.read.test.jdk;

/**
 * Created by yfwangrui on 2015/1/27.
 *
 * @see Object#finalize()
 */
public class ObjectTest {
    public void start() {
        System.out.println("start");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
        super.finalize();
    }
}