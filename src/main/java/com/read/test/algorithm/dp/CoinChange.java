package com.read.test.algorithm.dp;

import java.util.*;

public class CoinChange {

    /**
     * @see <a href="https://leetcode.cn/problems/coin-change/">零钱兑换</a>
     *
     * 递归法/dfs
     */
    public int coinChangeWithRecursion(int[] coins, int amount) {
        //使用HashMap来减少数组的内存占用
        Map<Integer, Integer> memo = new HashMap<>();
        return dp(coins, amount, memo);
    }

    public int dp(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int num = dp(coins, amount - coin, memo);
            if (num == -1) {
                continue;
            }
            res = Math.min(res, num + 1);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        memo.put(amount, res);
        return res;
    }

    /**
     * @see <a href="https://leetcode.cn/problems/coin-change/">零钱兑换</a>
     *
     * 迭代法
     */
    public int coinChangeWithIteration(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * @see <a href="https://leetcode.cn/problems/coin-change/">零钱兑换</a>
     *
     * BFS
     */
    public int coinChangeWithBFS(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];
        queue.offer(0);
        visited[0] = true;
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                for (int coin : coins) {
                    int next = current + coin;
                    if (next == amount) {
                        return level;
                    }
                    if (next > amount || visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return -1;
    }
}
