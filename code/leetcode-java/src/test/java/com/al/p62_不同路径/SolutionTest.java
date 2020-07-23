package com.al.p62_不同路径;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author duval
 * @date 2020-07-20 21:52
 */
public class SolutionTest extends TestCase {

    public void testUniquePaths() {
        Solution solution = new Solution();
        Assert.assertEquals(solution.uniquePaths(3, 2), 3);
        Assert.assertEquals(solution.uniquePaths(7, 3), 28);
    }
}