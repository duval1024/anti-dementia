package com.al.p123_买卖股票的最佳时机3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duval
 * @date 2020-07-26 22:44
 */
public class Solution {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int dp[][][] = new int[n][3][2];
        for (int i = 0; i < n; i++) {
            dp[i][0][1] = Integer.MIN_VALUE / 2;
        }

        dp[0][2][0] = Integer.MIN_VALUE / 2;
        dp[0][2][1] = Integer.MIN_VALUE / 2;
        dp[0][1][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[i][k][0] = Integer.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Integer.max(dp[i - 1][k - 1][0] - prices[i], dp[i - 1][k][1]);
            }
        }

        return Integer.max(dp[n - 1][2][0], dp[n-1][1][0]);
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}), 6);
        Assert.assertEquals(new Solution().maxProfit(new int[]{1, 2, 3, 4, 5}), 4);
        Assert.assertEquals(new Solution().maxProfit(new int[]{7, 6, 4, 3, 1}), 0);

        Assert.assertEquals(new Solution().maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}), 13);

    }
}
