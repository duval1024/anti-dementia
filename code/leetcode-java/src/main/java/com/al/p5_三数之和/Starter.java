package com.al.p5_三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        if (nums.length < 3) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        List<List<Integer>> finalResult = new ArrayList<>();

        for (int firstIndex = 0; firstIndex < len ; firstIndex++) {
            if (firstIndex > 0 && nums[firstIndex - 1] == nums[firstIndex]) {
                // 如果出现相同数字，跳过
                continue;
            }

            // 右指针从最右开始递减
            int thirdIndex = len - 1;
            // 左指针从最左开始迪增
            for (int secondIndex = firstIndex + 1; secondIndex < len; secondIndex++) {
                if (secondIndex > firstIndex + 1 && nums[secondIndex - 1] == nums[secondIndex]) {
                    continue;
                }

                // 右指针递减
                while (nums[firstIndex] + nums[secondIndex] + nums[thirdIndex] > 0 && thirdIndex > secondIndex) {
                    thirdIndex--;
                }

                // 左右指针重合后中断循环
                if (secondIndex == thirdIndex) {
                    break;
                }

                // 检查是否满足条件，满足则缓存组合
                if (nums[firstIndex] + nums[secondIndex] + nums[thirdIndex] == 0) {
                    finalResult.add(Arrays.asList(nums[firstIndex], nums[secondIndex], nums[thirdIndex]));
                }
            }
        }

        return finalResult;
    }
}

public class Starter {
    
}