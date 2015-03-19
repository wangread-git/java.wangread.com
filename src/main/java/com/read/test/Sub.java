package com.read.test;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 13-12-20
 * Time: 下午5:57
 * To change this template use File | Settings | File Templates.
 */
public class Sub extends Super {
    private final Date date;

    Sub() {
        date = new Date();
    }

    @Override
    public void overrideMe() {
        System.out.println(date);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
