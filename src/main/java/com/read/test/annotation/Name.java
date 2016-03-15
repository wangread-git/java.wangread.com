package com.read.test.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-26
 * Time: 下午3:55
 * To change this template use File | Settings | File Templates.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Name {
    String originate() default "hello";
    String community();
}
