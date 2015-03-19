package com.read.test.dubbo;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by yfwangrui on 2015/1/22.
 */
public class Provider {

    @Test
    public void start() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config-dubbo-provider.xml");
        context.start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
