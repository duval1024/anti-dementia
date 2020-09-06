package com.al.p560_和为K的子数组;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author duval
 * @date 2020-08-23 17:24
 */
public class Solution {

    public int subarraySum(int[] nums, int k) {
        // 计算前缀和数组
        for (int index = 1; index < nums.length; index++) {
            nums[index] += nums[index - 1];
        }

        // 两层循环暴力解决
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int leftSum = (i == 0) ? 0 : nums[i - 1];
            for (int j = i; j < nums.length; j++) {
                if (nums[j] - leftSum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    public int subarraySumByHashMap(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1);
        for (int index = 0; index < nums.length; index++) {
            nums[index] += (index == 0 ? 0 : nums[index - 1]);
            count += sumMap.getOrDefault(nums[index] - k, 0);
            sumMap.put(nums[index], sumMap.getOrDefault(nums[index], 0) + 1);
        }

        return count;
    }


    @Test
    public void test() {
        Assert.assertEquals(new Solution().subarraySum(new int[]{1, 1, 1}, 2), 2);
        Assert.assertEquals(new Solution().subarraySum(new int[]{1, 1, 1, 1, 1}, 3), 3);

        Assert.assertEquals(new Solution().subarraySumByHashMap(new int[]{1}, 1), 1);
        Assert.assertEquals(new Solution().subarraySumByHashMap(new int[]{1, 1, 1}, 2), 2);
        Assert.assertEquals(new Solution().subarraySumByHashMap(new int[]{1, 1, 1, 1, 1}, 3), 3);
    }
}
