package com.read.test.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by wang.read on 2016/1/28.
 *
 */
public class CglibTest {

    static {
        //配置class文件目录
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./target/classes");
    }

    @Test
    public void test() {
        final BaseClassImpl baseClass = new BaseClassImpl();
        baseClass.setWords("hello world!");
        final Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(this.getClass().getClassLoader());
        enhancer.setSuperclass(BaseClassImpl.class);
        enhancer.setInterceptDuringConstruction(false);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("start...");
            try {
                //这里要注意，如果是用obj来调用的话需要用invokeSuper方法
                return proxy.invoke(baseClass, args);
            } finally {
                System.out.println("end...");
            }
        });
        BaseClassImpl proxy = (BaseClassImpl) enhancer.create();
        proxy.foo();
        System.out.println(proxy.words);
    }
}
