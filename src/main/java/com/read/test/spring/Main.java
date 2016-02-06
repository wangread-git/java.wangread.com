package com.read.test.spring;


import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-7
 * Time: ����2:22
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    static {
        //配置class文件目录
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./target/classes");
    }

    public static void main(String[] args) {
//        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("hello.xml"));
        ApplicationContext context = new ClassPathXmlApplicationContext("hello.xml");
        GreetingService greetingService = (GreetingService) context.getBean("greetingService");/*(GreetingService) factory.getBean("greetingService");*/
        greetingService.sayGreeting();
    }
}
