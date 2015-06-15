package com.read.test.algorithm.dp;

import org.junit.Test;

/**
 * Created by yfwangrui on 2015/6/15.
 * <p/>
 * <a href="http://blog.csdn.net/v_JULY_v/article/details/6110269">longest common subsequence(string)</a>
 */
public class LCS {

    /**
     * longest common subsequence problem
     */
    @Test
    public void test1() {
        String str1 = "ABCBDAB";
        String str2 = "BDCABA";
        int m = str1.length();
        int n = str2.length();

        int[][] table = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }
        System.out.println(table[m][n]);
    }

    /**
     * longest common string problem
     */
    @Test
    public void test2() {
        String str1 = "ABCBDAB";
        String str2 = "BABCBD";
        int m = str1.length();
        int n = str2.length();

        int[][] table = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i > 1 && j > 1) {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1) && str1.charAt(i - 2) == str2.charAt(j - 2)) {
                        table[i][j] = table[i - 1][j - 1] + 1;
                    } else {
                        table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                    }
                } else {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        table[i][j] = 1;
                    }
                }
            }
        }
        System.out.println(table[m][n]);
    }
}
