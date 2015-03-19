package com.read.test.jdk.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by yfwangrui on 2015/2/2.
 */
public class Main {

    @Test
    public void test() {
        Person person = new PersonBean();
        Person personProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, new PersonInvocationHandler(person));
        personProxy.setName("hello");

//        PersonBean.InnerClass innerClass =(new PersonBean()).new InnerClass();
    }
}
