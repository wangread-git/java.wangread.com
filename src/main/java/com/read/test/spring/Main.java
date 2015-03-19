package com.read.test.spring;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-7
 * Time: ÏÂÎç2:22
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
//        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("hello.xml"));
        ApplicationContext context = new ClassPathXmlApplicationContext("hello.xml");
        GreetingService greetingService = (GreetingService) context.getBean("greetingService");/*(GreetingService) factory.getBean("greetingService");*/
        greetingService.sayGreeting();
//        GreetingService service = new GreetingServiceImpl();
//        Class clazz = service.getClass();
//        try {
//            Object obj = clazz.newInstance();
//            if (obj instanceof  GreetingService) {
//                System.out.println("1 " + true);
//            }
//            if (obj instanceof  GreetingServiceImpl) {
//                System.out.println("2 " + true);
//            }
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}
