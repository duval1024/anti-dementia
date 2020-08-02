package com.al.p1139_最大的以1位边界的正方形;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-08-02 16:36
 */
public class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // 递推构造关系图
        int row = grid.length;
        int col = grid[0].length;
        int dp[][][] = new int[row][col][2];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    dp[i][j][0] = (i - 1 < 0 ? 0 : dp[i - 1][j][0]) + 1;
                    dp[i][j][1] = (j - 1 < 0 ? 0 : dp[i][j - 1][1]) + 1;
                }
            }
        }

        // 遍历符合条件的正方形，并保存最大值到maxLength
        int maxLength = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int length = Integer.min(dp[i][j][0], dp[i][j][1]);
                    for (int k = length; k > 0; k--) {
                        if (dp[i - k + 1][j][1] >= k && dp[i][j - k + 1][0] >= k) {
                            maxLength = Integer.max(maxLength, k);
                        }
                    }
                }
            }
        }

        return maxLength * maxLength;
    }

    public int largest1BorderedSquareSimplify(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // 递推构造关系图
        int row = grid.length;
        int col = grid[0].length;
        int dp[][][] = new int[row][col][2];

        // 遍历符合条件的正方形，并保存最大值到maxLength
        int maxLength = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    dp[i][j][0] = (i - 1 < 0 ? 0 : dp[i - 1][j][0]) + 1;
                    dp[i][j][1] = (j - 1 < 0 ? 0 : dp[i][j - 1][1]) + 1;

                    int length = Integer.min(dp[i][j][0], dp[i][j][1]);
                    for (int k = length; k > 0; k--) {
                        if (dp[i - k + 1][j][1] >= k && dp[i][j - k + 1][0] >= k) {
                            maxLength = Integer.max(maxLength, k);
                        }
                    }
                }
            }
        }

        return maxLength * maxLength;
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().largest1BorderedSquare(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}), 9);
        Assert.assertEquals(new Solution().largest1BorderedSquare(new int[][]{{0, 0, 0, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}}), 9);

        Assert.assertEquals(new Solution().largest1BorderedSquare(new int[][]{{0, 1, 1, 1, 1, 0}, {1, 1, 0, 1, 1, 0}, {1, 1, 0, 1, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 0, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}}
        ), 16);


        Assert.assertEquals(new Solution().largest1BorderedSquareSimplify(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}), 9);
        Assert.assertEquals(new Solution().largest1BorderedSquareSimplify(new int[][]{{0, 0, 0, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}}), 9);

        Assert.assertEquals(new Solution().largest1BorderedSquareSimplify(new int[][]{{0, 1, 1, 1, 1, 0}, {1, 1, 0, 1, 1, 0}, {1, 1, 0, 1, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 0, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}}
        ), 16);
    }
}
