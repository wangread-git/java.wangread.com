package com.read.test.spi.impl;

import com.read.test.spi.Search;

/**
 * Created by yfwangrui on 2015/4/3.
 */
public class DataBaseSearch implements Search {
    public void search() {
        System.out.println("database search");
    }
}
