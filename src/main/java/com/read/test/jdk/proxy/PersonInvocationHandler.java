package com.read.test.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yfwangrui on 2015/2/2.
 */
public class PersonInvocationHandler implements InvocationHandler {

    private Person person;

    public PersonInvocationHandler(Person person) {
        this.person = person;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.startsWith("set")) {
            throw new IllegalAccessException();
        }
        return method.invoke(person, args);
    }
}
