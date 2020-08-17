package com.al.p523_连续的子数组和;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duval
 * @date 2020-08-12 22:24
 */
public class Solution {

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        for (int size = 2; size <= nums.length; size++) {
            for (int left = 0; left + size - 1 < nums.length; left++) {
                int leftSum = left == 0 ? 0 : nums[left - 1];
                int subSum = nums[left + size - 1] - leftSum;
                if ((subSum == 0 && k == 0) || (k != 0 && subSum % k == 0)) {
                    return true;
                }
            }
        }

        return false;
    }

//    public boolean checkSubarraySumHashMap(int[] nums, int k) {
//        if (nums == null || nums.length < 2) {
//            return false;
//        }
//
//        int sum = 0;
//        Map<Integer, Integer> resultMap = new HashMap<>(nums.length);
//        resultMap.put(0, -1);
//        for (int index = 0; index < nums.length; index++) {
//            sum += nums[index];
//
//            if (sum != 0 && k == 0) {
//                return false;
//            }
//
//            int remain = k != 0 ? sum % k : 0;
//            if (resultMap.containsKey(remain)) {
//                if (index - resultMap.get(remain) >= 1) {
//                    return true;
//                }
//            } else {
//                resultMap.put(remain, index);
//            }
//        }
//
//        return false;
//    }

    public boolean checkSubarraySumHashMap(int[] nums, int k) {
        int sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0)
                sum = sum % k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1)
                    return true;
            } else
                map.put(sum, i);
        }
        return false;
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6), true);
        Assert.assertEquals(new Solution().checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6), true);
        Assert.assertEquals(new Solution().checkSubarraySum(new int[]{0, 0}, 6), true);
        Assert.assertEquals(new Solution().checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 0), false);
//        Assert.assertEquals(new Solution().checkSubarraySum(new int[]{1, 0, 0}, 2), false);
        Assert.assertEquals(new Solution().checkSubarraySum(new int[]{0, 0, 0}, 2), true);


        Assert.assertEquals(new Solution().checkSubarraySumHashMap(new int[]{23, 2, 4, 6, 7}, 6), true);
        Assert.assertEquals(new Solution().checkSubarraySumHashMap(new int[]{23, 2, 6, 4, 7}, 6), true);
        Assert.assertEquals(new Solution().checkSubarraySumHashMap(new int[]{0, 0}, 6), true);
        Assert.assertEquals(new Solution().checkSubarraySumHashMap(new int[]{23, 2, 4, 6, 7}, 0), false);
        Assert.assertEquals(new Solution().checkSubarraySumHashMap(new int[]{1, 0}, 2), false);
//        Assert.assertEquals(new Solution().checkSubarraySumHashMap(new int[]{1, 0, 0}, 2), false);
        Assert.assertEquals(new Solution().checkSubarraySumHashMap(new int[]{0, 0, 0}, 2), true);



    }

}
