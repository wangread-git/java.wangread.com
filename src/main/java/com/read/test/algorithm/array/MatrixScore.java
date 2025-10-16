package com.read.test.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixScore {

    /**
     * @see <a href="https://leetcode.cn/problems/maximum-difference-score-in-a-grid/description/">矩阵中的最大得分</a>
     */
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] preMin = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(preMin[i], Integer.MAX_VALUE);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pre = Integer.MAX_VALUE;
                if (i > 0) {
                    pre = Math.min(pre, preMin[i - 1][j]);
                }
                if (j > 0) {
                    pre = Math.min(pre, preMin[i][j - 1]);
                }
                if (i > 0 || j > 0) {
                    max = Math.max(max, grid.get(i).get(j) - pre);
                }
                preMin[i][j] = Math.min(grid.get(i).get(j), pre);
            }
        }
        return max;
    }
}
