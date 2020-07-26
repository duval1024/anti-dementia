package com.al.p309_最佳买卖股票时机含冷冻期;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-07-26 15:40
 */
public class Solution {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;

        int dp[][] = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Integer.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Integer.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][2] = dp[i - 1][1] + prices[i];
        }

        return Integer.max(Integer.max(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
    }


    public int maxProfitCompress(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;

        int empty = 0; // dp[0][0] = 0;
        int hold = -prices[0]; // dp[0][1] = -prices[0];
        int freeze = 0; // dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            int nextEmpty = Integer.max(empty, freeze);
            int nextHold = Integer.max(empty - prices[i], hold);
            int nextFreeze = hold + prices[i];

            empty = nextEmpty;
            hold = nextHold;
            freeze = nextFreeze;
        }

        return Integer.max(Integer.max(empty, hold), freeze);
    }


    @Test
    public void test() {
        Assert.assertEquals(new Solution().maxProfit(new int[]{1, 2, 3, 0, 2}), 3);
        Assert.assertEquals(new Solution().maxProfit(new int[]{}), 0);
        Assert.assertEquals(new Solution().maxProfit(new int[]{1}), 0);

        Assert.assertEquals(new Solution().maxProfitCompress(new int[]{1, 2, 3, 0, 2}), 3);
        Assert.assertEquals(new Solution().maxProfitCompress(new int[]{}), 0);
        Assert.assertEquals(new Solution().maxProfitCompress(new int[]{1}), 0);

    }
}
