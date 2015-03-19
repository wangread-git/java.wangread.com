package com.read.test.spring.impl;

import com.read.test.spring.PrintSchedulerJob;

/**
 * Created by yfwangrui on 2015/3/5.
 */
public class PrintSchedulerJobImpl implements PrintSchedulerJob {
    public void execute() {
        System.out.println("execute at " + System.currentTimeMillis());
    }
}
