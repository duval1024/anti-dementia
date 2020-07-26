package com.al.p198_house_robber;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author duval
 * @date 2020-07-26 09:19
 */
public class Solution {

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int dp[][] = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Integer.max(dp[i - 1][1] , dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        return Integer.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }

    public int robCompress(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int preFalse = 0;
        int preTrue = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int nextFalse = Integer.max(preTrue, preFalse);
            int nextTrue = preFalse + nums[i];
            preFalse = nextFalse;
            preTrue = nextTrue;
        }

        return Integer.max(preFalse, preTrue);
    }

    public int robOneDimension(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }

        int pre = nums[0]; // dp[i-2]
        int sub = Integer.max(nums[0], nums[1]); // dp[i-1]

        for (int i = 2; i < nums.length; i++) {
            int next = Integer.max(pre + nums[i], sub);
            pre = sub;
            sub = next;
        }
        return sub;
    }

    @Test
    public void test() {
        Assert.assertEquals(rob(new int[]{1,2,3,1}), 4);
        Assert.assertEquals(rob(new int[]{2,7,9,3,1}), 12);

        Assert.assertEquals(robCompress(new int[]{1,2,3,1}), 4);
        Assert.assertEquals(robCompress(new int[]{2,7,9,3,1}), 12);


        Assert.assertEquals(robOneDimension(new int[]{1,2,3,1}), 4);
        Assert.assertEquals(robOneDimension(new int[]{2,7,9,3,1}), 12);

    }
}
