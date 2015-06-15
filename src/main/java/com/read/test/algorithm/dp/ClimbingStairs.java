package com.read.test.algorithm.dp;

import org.junit.Test;

/**
 * Created by yfwangrui on 2015/6/15.
 * <p/>
 * You are climbing a stair case. It takes n steps to reach to the top.
 * <p/>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p/>
 * <a href="https://leetcode.com/problems/climbing-stairs/">leetcode climbing stairs problem</a>
 */
public class ClimbingStairs {

    @Test
    public void test() {
        int n = 10;
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        System.out.println(f[n]);
    }
}
