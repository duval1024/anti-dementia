package com.al.p53_最大子序列和;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-08-01 15:47
 */
public class Solution {

    class Step {
        // 靠左区间
        int lsum;
        // 靠右区间
        int rsum;
        // 内部区间
        int isum;
        // 整个区间
        int asum;
    }

    public Step merge(Step left, Step right) {
        Step newStep = new Step();
        newStep.asum = left.asum + right.asum;
        newStep.lsum = Integer.max(left.lsum, left.asum + right.lsum);
        newStep.rsum = Integer.max(right.rsum, right.asum + left.rsum);
        newStep.isum = Integer.max(Integer.max(left.isum, right.isum), left.rsum + right.lsum);
        return newStep;
    }

    public Step getStep(int l, int r, int[] nums) {
        if (l == r) {
            Step newStep = new Step();
            newStep.lsum = nums[l];
            newStep.rsum = nums[l];
            newStep.isum = nums[l];
            newStep.asum = nums[l];
            return newStep;
        }

        int mid = (l + r) / 2;
        Step left = getStep(l, mid, nums);
        Step right = getStep(mid + 1, r, nums);
        return merge(left, right);
    }


    public int maxSubArrayBinary(int[] nums) {
        Step result = getStep(0, nums.length - 1, nums);
        return Integer.max(Integer.max(result.lsum, result.rsum), Integer.max(result.isum, result.asum));
    }


    public int maxSubArrayGreedy(int[] nums) {

        int maxSum = nums[0];
        int preSum = nums[0];
        for (int index = 1; index < nums.length; index++) {
            if (preSum < 0) {
                preSum = nums[index];
            } else {
                preSum += nums[index];
            }

            maxSum = Integer.max(maxSum, preSum);
        }

        return maxSum;
    }

    public int maxSubArray(int[] nums) {
        int pre = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            pre = Integer.max(nums[i], pre + nums[i]);
            max = Integer.max(max, pre);
        }
        return max;
    }


    @Test
    public void test() {
        Assert.assertEquals(new Solution().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6);
        Assert.assertEquals(new Solution().maxSubArrayBinary(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6);
        Assert.assertEquals(new Solution().maxSubArrayBinary(new int[]{-1}), -1);


        Assert.assertEquals(new Solution().maxSubArrayGreedy(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6);
        Assert.assertEquals(new Solution().maxSubArrayGreedy(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6);
        Assert.assertEquals(new Solution().maxSubArrayGreedy(new int[]{-1}), -1);
    }
}
