package com.al.p877_石子游戏;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-08-07 17:10
 */
public class Solution {

    public boolean stoneGameSimple(int[] piles) {
        int n = piles.length;
        int dp[][][] = new int[n + 2][n + 2][2];

        for (int size = 1; size <= n; size++) {
            for (int left = 0; left < n - size + 1; left++) {
                int right = left + size - 1;

                int leftChoice = dp[left + 2][right + 1][1] + piles[left];
                int rightChoice = dp[left + 1][right][1] + piles[right];

                if (leftChoice > rightChoice) {
                    dp[left + 1][right + 1][0] = leftChoice;
                    dp[left + 1][right + 1][1] = dp[left + 2][right + 1][0];
                } else {
                    dp[left + 1][right + 1][0] = rightChoice;
                    dp[left + 1][right + 1][1] = dp[left + 1][right][0];
                }
            }
        }

        return dp[1][n][0] > dp[1][n][1];
    }

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int dp[][] = new int[n + 2][n + 2];

        for (int size = 1; size <= n; size++) {
            for (int left = 0; left < n - size + 1; left++) {
                int right = left + size - 1;
                if ((right - left) % 2 == 0) {
                    dp[left + 1][right + 1] = Integer.max(dp[left + 2][right + 1] + piles[left], dp[left + 1][right] + piles[right]);
                } else {
                    dp[left + 1][right + 1] = Integer.min(dp[left + 2][right + 1] - piles[left], dp[left + 1][right] - piles[right]);
                }
            }
        }

        return dp[1][n] < 0;
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().stoneGameSimple(new int[]{5, 3, 4, 5}), true);
        Assert.assertEquals(new Solution().stoneGameSimple(new int[]{5, 1000, 4, 3}), true);
        Assert.assertEquals(new Solution().stoneGameSimple(new int[]{3, 2, 10, 4}), true);


        Assert.assertEquals(new Solution().stoneGame(new int[]{5, 3, 4, 5}), true);
        Assert.assertEquals(new Solution().stoneGame(new int[]{5, 1000, 4, 3}), true);
        Assert.assertEquals(new Solution().stoneGame(new int[]{3, 2, 10, 4}), true);

    }
}
