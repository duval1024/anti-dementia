package com.al.p4_Median_of_Two_Sorted_Arrays;


/**
 * @author duval
 * @date 2019-10-13 14:02
 */
public class Solution {


//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int sum = nums1.length + nums2.length;
//        boolean odd = sum % 2 == 0 ? false : true;
//        int middle = sum / 2;
//
//        int size = 0;
//        int index1 = 0;
//        int index2 = 0;
//        int lastValue = 0;
//        int currentValue = 0;
//        while (size < middle) {
//            if (index1 >= nums1.length && index2 < nums2.length) {
//                lastValue = nums2[index2];
//                index2++;
//            } else if (index1 < nums1.length && index2 >= nums2.length) {
//                lastValue = nums1[index1];
//                index1++;
//            } else if (nums1[index1]> nums2[index2]) {
//                lastValue = nums2[index2];
//                index2++;
//            } else {
//                lastValue = nums1[index1];
//                index1++;
//            }
//            size ++;
//        }
//
//        if (odd) {
//            return
//        }
//
//
//    }
}
