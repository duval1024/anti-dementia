package com.al.p1140_石子游戏2;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-08-08 14:42
 */
public class Solution {

    public int stoneGameII(int[] piles) {
        int len = piles.length, sum = 0;
        int dp[][] = new int[len][len + 1];

        for (int i = len - 1; i >= 0; i--) {
            sum += piles[i];
            for (int m = 1; m <= len; m++) {
                if (i + m * 2 >= len) {
                    dp[i][m] = sum;
                } else {
                    for (int x = 1; x <= 2 * m; x++) {
                        int nextM = Integer.max(x, m);
                        dp[i][m] = Integer.max(dp[i][m], sum - dp[i + x][nextM]);
                    }
                }
            }
        }
        return dp[0][1];
    }

    private int[][] memo;

    public int dfs(int[] piles, int index, int m) {
        if (index >= piles.length) {
            return 0;
        }

        if (memo[index][m] != 0) {
            return memo[index][m];
        }

        int max = 0;
        int x = 1;
        while (x <= 2 * m) {
            int nextM = Integer.max(m, x);
            max = Integer.max(max, piles[index] - dfs(piles, index + x, nextM));
            x++;
        }
        memo[index][m] = max;

        return max;
    }

    public int stoneGameIIDfs(int[] piles) {
        memo = new int[piles.length][piles.length];
        for (int index = piles.length - 1; index >= 0; index--) {
            int pre = index == piles.length - 1 ? 0 : piles[index + 1];
            piles[index] += pre;
        }

        return dfs(piles, 0, 1);
    }

    public void output(int[][] dp) {
        for (int m = 0; m < dp[0].length; m++) {
            System.out.println();
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[j][m] + " ");
            }
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().stoneGameII(new int[]{2, 7, 9, 4, 4}), 10);

        Assert.assertEquals(new Solution().stoneGameIIDfs(new int[]{2, 7, 9, 4, 4}), 10);

    }
}
