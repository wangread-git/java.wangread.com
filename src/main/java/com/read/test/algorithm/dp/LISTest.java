package com.read.test.algorithm.dp;

import org.junit.Test;

/**
 * Created by yfwangrui on 2015/6/15.
 * <p/>
 * longest increased subsequence problem
 */
public class LISTest {

    @Test
    public void test() {
        int[] seq = {1, 7, 2, 8, 3, 4};

        int[] f = new int[6];
        int max = 1;
        for (int i = 0; i < 6; i++) {
            f[i] = 1;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < i; j++) {
                if (seq[j] < seq[i]) {
                    f[i] = Math.max(f[i] , f[j] + 1);
                    max = Math.max(max, f[i]);
                }
            }
        }
        System.out.println(max);
    }
}
