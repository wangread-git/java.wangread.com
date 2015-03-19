package com.read.test.jdk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yfwangrui on 2014/12/12.
 */
public class ListTest {

    @Test
    public void test() {
        List list = new ArrayList();
        String str;
        for (Iterator i$ = list.iterator(); i$.hasNext(); str = (String)i$.next());
    }
}
