package com.read.test.jdk.proxy;

/**
 * Created by yfwangrui on 2015/2/2.
 */
public class PersonBean implements Person {

    public static String name;

    public PersonBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    class InnerClass {
        private final static String str = "";
    }
}
