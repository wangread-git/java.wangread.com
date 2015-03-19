package com.read.test.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

/**
 * Created by yfwangrui on 2014/9/29.
 */
public class ReceiverMain {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-activemq.xml");

        Connection conn;
        Session session;
        try {
            ActiveMQConnectionFactory connectionFactory = (ActiveMQConnectionFactory) context.getBean("connectionFactory");
            conn = connectionFactory.createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination mqTopic = (Destination) context.getBean("mqTopic");
            MessageConsumer consumer = session.createConsumer(mqTopic);
            MessageListener listener = (MessageListener) context.getBean("bookMessageListener");
//            consumer.setMessageListener(listener);
            conn.start();
            Message message = consumer.receive();
            System.out.println(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
