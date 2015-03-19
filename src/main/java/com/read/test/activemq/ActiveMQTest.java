package com.read.test.activemq;

import com.read.test.domain.Book;
import org.springframework.jms.core.JmsTemplate;

/**
 * Created by yfwangrui on 2014/9/29.
 */
public class ActiveMQTest {

    private JmsTemplate jmsTemplate;

    public void sendMessage(final Book book) {
        jmsTemplate.convertAndSend(book);
    }

    public void receive() {
        Book book = (Book) jmsTemplate.receiveAndConvert();
        if (book != null) {
            System.out.println(book.toString());
        }
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
