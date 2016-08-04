package com.read.test.spring;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-7
 * Time: 下午2:20
 * To change this template use File | Settings | File Templates.
 */
public interface GreetingService {
    public void sayGreeting();

    public void test(Map<String, List<String>> map);
}
