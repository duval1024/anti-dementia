package com.al.p1343_大小为K且平均值大于等于阈值的子数组数目;

import org.junit.Test;

/**
 * @author duval
 * @date 2019-10-13 14:43
 */
public class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int num = 0;

        int length = arr.length;
        // 滑动窗口左右边界
        int left = 0;
        int right = k;
        int sum = 0;

        for (int index = left; index < right; index++) {
            sum += arr[index];
        }

        while (true) {
            // 判断是否满足阈值要求
            if (sum / k >= threshold) {
                num++;
            }

            // 滑动窗口终止条件
            if (right + 1 > length) {
                break;
            }

            // 变更总和，并滑动窗口
            sum -= arr[left++];
            sum += arr[right++];
        }

        return num;
    }

    @Test
    public void test() {
        assert numOfSubarrays(new int[]{2, 2, 2, 2, 5, 5, 5, 8}, 3, 4) == 3;
    }
}
