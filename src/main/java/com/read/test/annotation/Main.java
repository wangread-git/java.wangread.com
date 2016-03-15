package com.read.test.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-26
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    private final static Log log = LogFactory.getLog(Main.class);

    public final static String CLASS_NAME = "com.read.test.annotation.AnnotationTest";

//    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
//        Class clazz = null;
//        try {
//            clazz = Class.forName(CLASS_NAME);
//        } catch (ClassNotFoundException e) {
//            log.error(e);
//        }
//        if(clazz != null) {
//            Method[] methods = clazz.getMethods();
//            boolean flag = clazz.isAnnotationPresent(Description.class);
//            if(flag) {
//                Description description = (Description) clazz.getAnnotation(Description.class);
//                System.out.println("description��" + description.value());
//            }
//
//            Set<Method> methodSet = new HashSet<Method>();
//            for (Method method : methods) {
//                boolean flag2 = method.isAnnotationPresent(Name.class);
//                if(flag2) {
//                    methodSet.add(method);
//                }
//            }
//            for (Method method : methodSet) {
//                Name name = method.getAnnotation(Name.class);
//                System.out.print(name.community());
//                System.out.println(name.originate());
//            }
//        }
//        boolean flag = true;
//        System.out.print(test(flag));
//        Object obj = null;
//        if (test(obj)) {
//            System.out.println("true");
//        }
//        String name = "%E7%AD%BE%E5%88%B0%E6%B5%8B%E8%AF%95009";
//        try {
//            name = URLDecoder.decode(name, "utf-8");
//            System.out.println(name);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }

        System.out.println(new Integer(1) == null);
    }

    public static boolean test(Object o) {
        return o instanceof Boolean;
    }
}
