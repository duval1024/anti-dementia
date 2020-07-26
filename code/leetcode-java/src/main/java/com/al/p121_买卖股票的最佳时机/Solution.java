package com.al.p121_买卖股票的最佳时机;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-07-26 21:38
 */
public class Solution {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int index = 1; index < prices.length; index++) {
            if (prices[index] < minPrice) {
                minPrice = prices[index];
            } else {
                maxProfit = Integer.max(prices[index] - minPrice, maxProfit);
            }
        }

        return maxProfit;
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4}), 5);
        Assert.assertEquals(new Solution().maxProfit(new int[]{7,6,4,3,1}), 0);
        Assert.assertEquals(new Solution().maxProfit(new int[]{7}), 0);
        Assert.assertEquals(new Solution().maxProfit(new int[]{}), 0);
        Assert.assertEquals(new Solution().maxProfit(new int[]{1,2,3,4,5,6,7}), 6);



    }
}
