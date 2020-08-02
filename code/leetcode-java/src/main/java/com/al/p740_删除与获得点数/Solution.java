package com.al.p740_删除与获得点数;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duval
 * @date 2020-08-02 21:08
 */
public class Solution {


    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = nums[0];
        int min = nums[0];
        for (int num : nums) {
            max = Integer.max(max, num);
            min = Integer.min(min, num);
        }

        int dp[][] = new int[max + 1 - min][3];
        for (int num : nums) {
            dp[num - min][2] += num;
        }

        dp[0][1] = dp[0][2];
        for (int i = 1; i <= max - min; i++) {
            dp[i][0] = Integer.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + dp[i][2];
        }

        return Integer.max(dp[max - min][0], dp[max - min][1]);
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().deleteAndEarn(new int[]{3, 4, 2}), 6);
        Assert.assertEquals(new Solution().deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}), 9);
        Assert.assertEquals(new Solution().deleteAndEarn(new int[]{1}), 1);


    }
}