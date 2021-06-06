package com.al;

import static org.junit.Assert.assertTrue;

import com.al.剑指53_0_N中缺失的数字.Solution;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( new Solution().missingNumber(new int[]{0,1,3}) == 2);
        assertTrue( new Solution().missingNumber(new int[]{0,1,2,3,4,5,6,7,9}) == 8);
        assertTrue( new Solution().missingNumber(new int[]{0}) == 1);


//        assertTrue( new Solution().missingNumber(new int[]{0,1,2,3,4,5,6,7,8}) == 9);

    }
}
