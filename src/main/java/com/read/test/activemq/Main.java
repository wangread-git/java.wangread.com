package com.read.test.activemq;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yfwangrui on 2014/10/8.
 */
public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-activemq-consumer.xml");
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        System.out.println(list.toString());
    }
}
