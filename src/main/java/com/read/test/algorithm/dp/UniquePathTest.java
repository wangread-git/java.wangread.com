package com.read.test.algorithm.dp;

import org.junit.Test;

/**
 * Created by yfwangrui on 2015/6/12.
 *
 * unique paths problem
 * <a href="https://leetcode.com/problems/unique-paths-ii/">Unique Paths II</a>
 */
public class UniquePathTest {

    @Test
    public void test() {
        int[][] grid = {{0, 0, 0}, {0, 0, 0}, {0, 1, 0}};
        int row = grid.length + 1;
        int col = grid[0].length + 1;
        int[][] path = new int[row][col];
        if (grid[0][0] == 0) {
            path[1][1] = 1;
        } else {
            path[1][1] = 0;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i - 1][j - 1] == 1) {
                    path[i][j] = 0;
                } else {
                    if (i > 1) {
                        if (grid[i - 2][j - 1] == 0) {
                            path[i][j] += path[i - 1][j];
                        }
                    }
                    if (j > 1) {
                        if (grid[i - 1][j - 2] == 0) {
                            path[i][j] += path[i][j - 1];
                        }
                    }
                }
            }
        }
        System.out.println(path[row - 1][col - 1]);
    }
}
