package com.read.test.jdk;

import com.read.test.guava.cache.NullObject;
import org.junit.Test;

/**
 * Created by yfwangrui on 2015/1/28.
 */
public class EnumTest {

    @Test
    public void test() {
        System.out.println(NullObject.NULL_OBJECT.name());
        System.out.println(NullObject.NULL_OBJECT.ordinal());
    }
}
