package com.read.test.algorithm.array;

/**
 * @see <a href="https://leetcode.cn/problems/range-sum-query-2d-immutable/">二维前缀和</a>
 */
public class NumMatrix {

    private int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return;
        }
        int n = matrix[0].length;
        preSum = new int[m + 1][n + 1];
        if (n == 0) {
            return;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j]
                        + preSum[i][j - 1]
                        + matrix[i - 1][j - 1]
                        - preSum[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1]
                - preSum[row1][col2 + 1]
                - preSum[row2 + 1][col1]
                + preSum[row1][col1];
    }
}
