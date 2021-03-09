package com.al.p1_Two_Sum;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duval
 * @date 2019-10-13 11:15
 */
public class Solution {
//    public int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    int[] result = {i, j};
//                    return result;
//                }
//            }
//        }
//        return null;
//    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if (map.containsKey(remain) && !map.get(remain).equals(i)) {
                return new int[]{i, map.get(remain)};
            }
        }

        throw new RuntimeException();
    }

    /**
     * 更高效的一次遍历
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer,Integer> numToIndexMap = new HashMap<>(nums.length);
        for (int index = 0; index < nums.length; index++) {
            int targetNum = target - nums[index];
            if (numToIndexMap.containsKey(targetNum)) {
                return new int[]{index, numToIndexMap.get(targetNum)};
            } else {
                numToIndexMap.put(nums[index], index);
            }
        }

        return null;
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] input = {2,7,12,3};
        int[] result = solution.twoSum(input, 9);
        assert result[0] == 0;
        assert result[1] == 1;

    }
}
