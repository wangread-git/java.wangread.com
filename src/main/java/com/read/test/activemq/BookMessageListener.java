package com.read.test.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by yfwangrui on 2014/10/8.
 */
public class BookMessageListener implements MessageListener {

    public void onMessage(Message message) {
        try {

            if (message != null) {
                System.out.println(((TextMessage) message).getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
