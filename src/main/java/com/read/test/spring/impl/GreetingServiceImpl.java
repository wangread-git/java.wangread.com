package com.read.test.spring.impl;

import com.read.test.spring.GreetingService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-7
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 */
public class GreetingServiceImpl implements GreetingService{

    private String greeting;

    public void sayGreeting() {
//        for (String s : strings) {
//            System.out.println(s);
//        }
        System.out.println(greeting);
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
