package com.read.test.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

/**
 * Created by yfwangrui on 2014/10/8.
 */
public class SenderMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-activemq.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
        Destination destination = (Destination) context.getBean("mqTopic");
        jmsTemplate.setDefaultDestination(destination);
        ActiveMQTest test = new ActiveMQTest();
        test.setJmsTemplate(jmsTemplate);
//        Book book = new Book();
//        book.setId(2);
//        book.setName("java并发编程实战");
//        test.sendMessage(book);
    }
}
