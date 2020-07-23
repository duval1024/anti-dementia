package com.al.p714_买卖股票的最佳时机含手续费;

import junit.framework.TestCase;
import org.junit.Assert;

/**
 * @author duval
 * @date 2020-07-22 22:18
 */
public class SolutionTest extends TestCase {

    public void testMaxProfit() {
        Assert.assertEquals(new Solution().maxProfit(new int []{1,3,2,8,4,9}, 2), 8);
        Assert.assertEquals(new Solution().maxProfit(new int []{1,5,2,8}, 2), 6);
        Assert.assertEquals(new Solution().maxProfit(new int []{1,1,1,1}, 2), 0);
        Assert.assertEquals(new Solution().maxProfit(new int []{2,1,4,4,2,3,2,5,1,2}, 1),4);


    }
}