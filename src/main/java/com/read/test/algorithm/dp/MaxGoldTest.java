package com.read.test.algorithm.dp;

import org.junit.Test;

import java.util.*;

/**
 * Created by yfwangrui on 2015/6/10.
 * <p/>
 * 有n个金矿，m个人，每座金矿可以开采gold[i]的金子，需要people[i]个人，求有peopleNum个人时，最多可以开采多少金矿
 */
public class MaxGoldTest {
    private int[] gold = {8, 9, 10, 7, 6};
    private int[] people = {5, 6, 7, 4, 3};
    private Map<String, Integer> cache = new HashMap<String, Integer>();

    @Test
    public void test() {
        long start = System.nanoTime();
        System.out.println(solution1(20, 4));
        long end = System.nanoTime();
        System.out.println(end - start);
        System.out.println(solution2(20, 5));
        long end2 = System.nanoTime();
        System.out.println(end2 - end);
    }

    private int solution1(int p, int i) {
        String key = p + "-" + i;
        int goldNum;
        //减少计算次数
        if (cache.get(key) != null) {
            goldNum = cache.get(key);
        } else if (i == 0) {    //边界
            if (p >= people[i]) {
                goldNum = gold[i];
            } else {
                goldNum = 0;
            }
        } else if (p >= people[i]) {
            int result1 = solution1(p, i - 1); //不开采本矿
            int result2 = solution1(p - people[i], i - 1) + gold[i];  //开采本矿
            if (result1 >= result2) {
                goldNum = result1;
            } else {
                goldNum = result2;
            }
        } else {
            goldNum = solution1(p, i - 1);
        }
        cache.put(key, goldNum);
        return goldNum;
    }

    private int solution2(int m, int n) {
        int[][] value = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            value[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            value[0][i] = 0;
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (people[i - 1] <= j) {
                    int result1 = value[i - 1][j - people[i - 1]] + gold[i - 1];
                    int result2 = value[i - 1][j];
                    value[i][j] = Math.max(result1, result2);
                } else {
                    value[i][j] = value[i - 1][j];
                }
                max = Math.max(value[i][j], max);
            }
        }
        return max;
    }

}
