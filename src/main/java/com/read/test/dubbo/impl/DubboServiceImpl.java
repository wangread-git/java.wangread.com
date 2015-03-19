package com.read.test.dubbo.impl;

import com.read.test.dubbo.DubboService;

/**
 * Created by yfwangrui on 2015/1/22.
 */
public class DubboServiceImpl implements DubboService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
