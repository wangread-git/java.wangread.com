package com.read.test.javassist;

import com.read.test.jdk.proxy.Person;
import com.read.test.service.Service;
import com.read.test.service.impl.ServiceImpl;
import javassist.*;
import javassist.bytecode.ClassFile;
import javassist.util.proxy.FactoryHelper;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wang.read on 2016/1/28.
 */
public class JavassistTest {
    @Test
    public void test() {
        ClassPool classPool = new ClassPool(true);
        classPool.importPackage(Service.class.getPackage().getName());
        Service delegate = new ServiceImpl();
        try {
            CtClass ctClass = classPool.makeClass("ServiceImpl$Javassist$Proxy");
            ctClass.addInterface(classPool.get(Service.class.getName()));
            ctClass.addConstructor(CtNewConstructor.defaultConstructor(ctClass));
            ctClass.addField(CtField.make("private Service delegate;", ctClass));
            ctClass.addMethod(CtNewMethod.make("public void foo(String str) {System.out.println(\"before invoke\"); this.delegate.foo($1); System.out.println(\"after invoke\");}", ctClass));

            Class<?> clazz = ctClass.toClass();

            Service proxy = (Service) clazz.newInstance();
            Field field = clazz.getDeclaredField("delegate");
            field.setAccessible(true);
            field.set(proxy, delegate);
            proxy.foo("bar");
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProxy() {
        Service delegate = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[]{Service.class});
        proxyFactory.writeDirectory = "./target/classes";
        Class proxyClass = proxyFactory.createClass();
        try {
            Service proxy = (Service) proxyClass.newInstance();
            ((ProxyObject)proxy).setHandler(new ServiceMethodHandler(delegate));
            proxy.foo("bar");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    class ServiceMethodHandler implements MethodHandler {

        final Service delegate;

        public ServiceMethodHandler(Service delegate) {
            this.delegate = delegate;
        }

        public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
            return thisMethod.invoke(delegate, args);
        }
    }

    @Test
    public void genJdkProxyClassFile() {
        String proxyName = "com.sun.proxy.$Proxy2";
        byte[] data = ProxyGenerator.generateProxyClass(proxyName, new Class[] {Service.class});
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bis);
        try {
            ClassFile cf = new ClassFile(dis);
            FactoryHelper.writeFile(cf, "./target/classes");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }
}
