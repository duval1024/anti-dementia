package com.al.p122_买卖股票的最佳时机2;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-07-26 22:01
 */
public class Solution {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int maxProfit = 0;
        for (int index = 1; index < prices.length; index++) {
            if (prices[index] > prices[index - 1]) {
                maxProfit += (prices[index] - prices[index - 1]);
            }
        }

        return maxProfit;
    }


    @Test
    public void test() {
        Assert.assertEquals(new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4}), 7);
        Assert.assertEquals(new Solution().maxProfit(new int[]{1, 2, 3, 4, 5}), 4);
        Assert.assertEquals(new Solution().maxProfit(new int[]{1, 1, 1, 1, 1}), 0);
        Assert.assertEquals(new Solution().maxProfit(new int[]{5, 4, 3, 2, 1}), 0);


    }
}
