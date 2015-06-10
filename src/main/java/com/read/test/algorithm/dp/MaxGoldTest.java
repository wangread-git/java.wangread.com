package com.read.test.algorithm.dp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println(calculate(20, 4));
    }

    private int calculate(int p, int i) {
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
            int result1 = calculate(p, i - 1); //不开采本矿
            int result2 = calculate(p - people[i], i - 1) + gold[i];  //开采本矿
            if (result1 >= result2) {
                goldNum = result1;
            } else {
                goldNum = result2;
            }
        } else {
            goldNum = calculate(p, i - 1);
        }
        cache.put(key, goldNum);
        return goldNum;
    }
}
