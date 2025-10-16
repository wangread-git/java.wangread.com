package com.read.test.algorithm.dp;

public class Fibonacci {

    /**
     * @see <a href="https://leetcode.cn/problems/fibonacci-number/description/">斐波那契数</a>
     */
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int n_2, n_1 = 0, r = 1;
        for (int i = 2; i <= n; i++) {
            n_2 = n_1;
            n_1 = r;
            r = n_1 + n_2;
        }
        return r;
    }
}
