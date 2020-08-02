package com.al.p300_最长上升子序列;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-08-01 17:58
 */
public class Solution {

    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int dp[] = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Integer.max(dp[j] + 1, dp[i]);
                }
            }
            max = Integer.max(max, dp[i]);
        }

        return max;
    }

    public int lengthOfLISBinary(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int d[] = new int[nums.length];
        d[0] = nums[0];
        int maxLength = 1;
        for (int index = 1; index < nums.length; index++) {
            if (nums[index] > d[maxLength - 1]) {
                d[maxLength] = nums[index];
                maxLength++;
            } else {
                int l = 0;
                int r = maxLength - 1;

                while (l <= r) {
                    if (l == r) {
                        if (d[l] >= nums[index]) {
                            d[l] = nums[index];
                        } else {
                            d[l + 1] = nums[index];
                        }
                        break;
                    }

                    int mid = (l + r) / 2;
                    if (d[mid] < nums[index]) {
                        l = (mid + 1 < r) ? mid + 1 : r;
                    } else {
                        r = (mid - 1 > l) ? mid - 1 : l;
                    }
                }
            }
        }

        return maxLength;
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}), 4);
        Assert.assertEquals(new Solution().lengthOfLIS(new int[]{}), 0);
        Assert.assertEquals(new Solution().lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}), 6);


        Assert.assertEquals(new Solution().lengthOfLISBinary(new int[]{10, 9, 2, 5, 3, 7, 101, 18}), 4);
        Assert.assertEquals(new Solution().lengthOfLISBinary(new int[]{}), 0);
        Assert.assertEquals(new Solution().lengthOfLISBinary(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}), 6);

        Assert.assertEquals(new Solution().lengthOfLISBinary(new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12}), 6);

    }

}
