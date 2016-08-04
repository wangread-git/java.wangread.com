package com.read.test.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.read.test.spring.GreetingService;
import com.read.test.spring.impl.GreetingServiceImpl;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by bjyfwangrui on 2016/8/4.
 * <p>
 * fastjson demo
 */
public class FastjsonDemo {
    @Test
    public void testReflect() {
        GreetingService service = new GreetingServiceImpl();
        Class clazz = (Class) service.getClass().getGenericInterfaces()[0];
        Method[] methods = clazz.getMethods();
        Method method = methods[0];
        Type[] types = method.getGenericParameterTypes();
        TypeReference[] typeReferences = new TypeReference[types.length];
        for (int i = 0; i < types.length; i++) {
            Type actualType = types[i];
            typeReferences[i] = new TypeReference<Object>() {
            };
            Class typeClazz = typeReferences[i].getClass();
            try {
                Field field = typeClazz.getSuperclass().getDeclaredField("type");
                field.setAccessible(true);
                field.set(typeReferences[i], actualType);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String json = "{\"name\":[\"hello\",\"world\"]}";
        try {
            method.invoke(service, JSON.parseObject(json, typeReferences[0]));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
