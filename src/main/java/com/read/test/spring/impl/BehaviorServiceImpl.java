package com.read.test.spring.impl;

import com.read.test.spring.BehaviorService;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-7
 * Time: ����3:29
 * To change this template use File | Settings | File Templates.
 */
public class BehaviorServiceImpl implements BehaviorService, BeanNameAware, InitializingBean {

    String beanName;

    public void shake() {
        System.out.println("����");
    }

    public void swapCard() {
        System.out.println("��������Ƭ");
    }

    public void setBeanName(String name) {
        this.beanName = name;
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println(beanName);
    }
}
