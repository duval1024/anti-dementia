package com.al.p91_解码方法;

import junit.framework.TestCase;
import org.junit.Assert;

/**
 * @author duval
 * @date 2020-07-23 22:32
 */
public class SolutionTest extends TestCase {

    public void testNumDecodings() {

        Assert.assertEquals(new Solution().numDecodings("12"), 2);
        Assert.assertEquals(new Solution().numDecodings("226"), 3);
        Assert.assertEquals(new Solution().numDecodings("2261"), 3);
        Assert.assertEquals(new Solution().numDecodings("0"), 0);
        Assert.assertEquals(new Solution().numDecodings("1"), 1);
        Assert.assertEquals(new Solution().numDecodings("1001"), 0);
        Assert.assertEquals(new Solution().numDecodings("01"), 0);


        Assert.assertEquals(new Solution().numDecodingsSimplify("12"), 2);
        Assert.assertEquals(new Solution().numDecodingsSimplify("226"), 3);
        Assert.assertEquals(new Solution().numDecodingsSimplify("2261"), 3);
        Assert.assertEquals(new Solution().numDecodingsSimplify("0"), 0);
        Assert.assertEquals(new Solution().numDecodingsSimplify("1"), 1);
        Assert.assertEquals(new Solution().numDecodingsSimplify("1001"), 0);
        Assert.assertEquals(new Solution().numDecodingsSimplify("01"), 0);


    }
}