package com.al.剑指53_0_N中缺失的数字;

/**
 * @author duval
 * @date 2020-07-20 22:47
 */
public class Solution {
    public int missingNumber1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }



//    private int binarySearch(int[] nums, int left, int right) {
//        if (right - left == 1) {
//            return left + 1;
//        }
//
//        int mid = (left + right) / 2;
//        if ((mid - left) < (nums[mid] - nums[left])) {
//            return binarySearch(nums, left, mid);
//        } else {
//            return binarySearch(nums, mid, right);
//        }
//    }
//
//    public int missingNumber(int[] nums) {
//        if (nums[0] != 0) {
//            return 0;
//        } else if (nums[nums.length - 1] != nums.length) {
//            return nums.length;
//        }
//
//        return binarySearch(nums, 0, nums.length - 1);
//    }


        public int missingNumber(int[] nums) {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] != mid) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }




}
