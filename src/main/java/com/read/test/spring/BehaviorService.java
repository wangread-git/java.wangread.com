package com.read.test.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-7-7
 * Time:下午3:28
 * To change this template use File | Settings | File Templates.
 */
public interface BehaviorService {

    public void shake(JoinPoint jp);

    public void swapCard(JoinPoint jp);
}
