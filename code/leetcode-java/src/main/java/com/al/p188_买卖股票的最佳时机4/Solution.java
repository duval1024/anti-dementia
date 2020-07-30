package com.al.p188_买卖股票的最佳时机4;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-07-30 21:59
 */
public class Solution {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int dp[][][] = new int[n][k + 1][2];
        for (int i = 1; i < k + 1; i++) {
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Integer.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Integer.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
            }
        }

        int max = 0;
        for (int i = 1; i < k + 1; i++) {
            max = Integer.max(max, dp[n - 1][i][0]);
        }

        return max;
    }

    public int maxProfitCompress(int k, int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int dp[][][] = new int[2][k + 1][2];
        for (int i = 1; i < k + 1; i++) {
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i % 2][j][0] = Integer.max(dp[(i - 1) % 2][j][0], dp[(i - 1) % 2][j][1] + prices[i]);
                dp[i % 2][j][1] = Integer.max(dp[(i - 1) % 2][j - 1][0] - prices[i], dp[(i - 1) % 2][j][1]);
            }
        }

        int max = 0;
        for (int i = 1; i < k + 1; i++) {
            max = Integer.max(max, dp[(n - 1) % 2][i][0]);
        }

        return max;
    }


    public int maxProfitCompress1(int k, int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int dp[][] = new int[k + 1][2];
        for (int i = 1; i < k + 1; i++) {
            dp[i][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k + 1; j++) {
                dp[j][0] = Integer.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Integer.max(dp[j - 1][0] - prices[i],  dp[j][1]);
            }
        }

        int max = 0;
        for (int i = 1; i < k + 1; i++) {
            max = Integer.max(max, dp[i][0]);
        }

        return max;
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().maxProfit(2, new int[]{3, 3, 5, 0, 0, 3, 1, 4}), 6);
        Assert.assertEquals(new Solution().maxProfit(2, new int[]{1, 2, 3, 4, 5}), 4);
        Assert.assertEquals(new Solution().maxProfit(2, new int[]{7, 6, 4, 3, 1}), 0);

        Assert.assertEquals(new Solution().maxProfit(2, new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}), 13);


        Assert.assertEquals(new Solution().maxProfitCompress(2, new int[]{3, 3, 5, 0, 0, 3, 1, 4}), 6);
        Assert.assertEquals(new Solution().maxProfitCompress(2, new int[]{1, 2, 3, 4, 5}), 4);
        Assert.assertEquals(new Solution().maxProfitCompress(2, new int[]{7, 6, 4, 3, 1}), 0);

        Assert.assertEquals(new Solution().maxProfitCompress(2, new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}), 13);


        Assert.assertEquals(new Solution().maxProfitCompress1(2, new int[]{3, 3, 5, 0, 0, 3, 1, 4}), 6);
        Assert.assertEquals(new Solution().maxProfitCompress1(2, new int[]{1, 2, 3, 4, 5}), 4);
        Assert.assertEquals(new Solution().maxProfitCompress1(2, new int[]{7, 6, 4, 3, 1}), 0);

        Assert.assertEquals(new Solution().maxProfitCompress1(2, new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}), 13);
    }
}
