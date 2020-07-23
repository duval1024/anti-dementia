package com.al.p1144_递减元素使数组呈锯齿状;

import org.junit.Assert;
import org.junit.Test;

import javax.print.attribute.standard.NumberUp;

/**
 * @author duval
 * @date 2019-10-13 14:43
 */
public class Solution {

    public int movesToMakeZigzag(int[] nums) {
        int odd = 0, even = 0;
        for (int i = 0; i < nums.length; i++) {
            int op = 0;
            int left = (i - 1 >= 0) ? nums[i - 1] : Integer.MAX_VALUE;
            int right = (i + 1 < nums.length) ? nums[i + 1] : Integer.MAX_VALUE;

            int min = Integer.min(left, right);
            if (min <= nums[i]) {
                op = (nums[i] - min + 1);
            }

            if ((i & 1) == 1) {
                odd += op;
            } else {
                even += op;
            }
        }

        return Integer.min(odd, even);
    }

    @Test
    public void test() {
        Assert.assertEquals(movesToMakeZigzag(new int[]{1, 2, 3}), 2);
        Assert.assertEquals(movesToMakeZigzag(new int[]{9, 6, 1, 6, 2}), 4);
        Assert.assertEquals(movesToMakeZigzag(new int[]{2, 7, 10, 9, 8, 9}), 4);

    }

    @Test
    public void tet1() {
        String string = new String("Здравствуйте");
        System.out.println(string.length());
        System.out.println(string.charAt(2));
        for (byte b : string.getBytes()) {
            System.out.print(b);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println(string.getBytes().length);

        String str = "नमस्ते";
        System.out.println(str.length());
        System.out.println(str.getBytes().length);
    }
}
