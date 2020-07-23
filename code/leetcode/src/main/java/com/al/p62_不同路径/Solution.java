package com.al.p62_不同路径;

import java.util.Arrays;
import java.util.Map;

/**
 * @author duval
 * @date 2020-07-20 21:52
 */
class Solution {

    public int uniquePaths1(int m, int n) {
        int[][] matrix = new int[m][n];

        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                if (row == 0 || column == 0) {
                    matrix[row][column] = 1;
                    continue;
                } else {
                    matrix[row][column] = matrix[row - 1][column] + matrix[row][column - 1];
                }
            }
        }


        return matrix[m - 1][n - 1];
    }

    // 2*n数组压缩
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[2][n];

        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                if (row == 0 || column == 0) {
                    dp[row % 2][column] = 1;
                    continue;
                } else {
                    dp[row % 2][column] = dp[(row - 1) % 2][column] + dp[row % 2][column - 1];
                }
            }
        }

        return dp[(m - 1) % 2][n - 1];
    }

    // 一维数组压缩
    public int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j - 1] + dp[j];
            }
        }
        return dp[n - 1];
    }
}
