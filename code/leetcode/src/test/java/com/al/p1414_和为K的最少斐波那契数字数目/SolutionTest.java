package com.al.p1414_和为K的最少斐波那契数字数目;

import junit.framework.TestCase;
import org.junit.Assert;

/**
 * @author duval
 * @date 2020-07-21 22:49
 */
public class SolutionTest extends TestCase {

    public void testFindMinFibonacciNumbers() {
        Assert.assertEquals(new Solution().findMinFibonacciNumbers(7), 2);
        Assert.assertEquals(new Solution().findMinFibonacciNumbers(10), 2);
        Assert.assertEquals(new Solution().findMinFibonacciNumbers(19), 3);
        Assert.assertEquals(new Solution().findMinFibonacciNumbers(89), 1);
    }
}