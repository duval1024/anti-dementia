package com.al.p978_最长湍流子数组;

import junit.framework.Assert;

class Solution {
    // public int maxTurbulenceSize(int[] arr) {
    // if (arr.length < 3) {
    // return arr.length;
    // }

    // // dp[i][0] 表示arr[i]相对较小时的最长湍流子数组长度
    // // dp[i][1] 表示arr[i]相对较大时的最长湍流子数组长度
    // int dp[][] = new int[arr.length][2];
    // dp[0][0] = 1;
    // dp[0][1] = 1;
    // for (int i = 1; i < arr.length; i++) {
    // for (int j = 0; j < i; j++) {
    // if (arr[i] > arr[j]) {
    // dp[i][1] = Integer.max(dp[i][1], dp[j][0] + 1);
    // dp[i][0] = Integer.max(dp[i][0], dp[j][0]);
    // } else if (arr[i] < arr[j]) {
    // dp[i][0] = Integer.max(dp[i][0], dp[j][1] + 1);
    // dp[i][1] = Integer.max(dp[i][1], dp[j][1]);
    // } else {
    // dp[i][0] = Integer.max(dp[i][0], dp[j][0]);
    // dp[i][1] = Integer.max(dp[i][1], dp[j][1]);
    // }
    // }
    // }
    // return Integer.max(dp[arr.length - 1][0], dp[arr.length - 1][1]);
    // }

    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return n;
        }
        int dp[][] = new int[n][n];
        for (int index = 0; index < n; index++) {
            dp[index][index] = 1;
            if (index + 1 < n) {
                dp[index][index + 1] = 2;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int left = 0; left + len - 1 < n; left++) {
                int right = left + len - 1;

                if (arr[right] > arr[right - 1] && arr[right - 1] < arr[right - 2]) {
                    dp[left][right] = dp[left][right - 1] + 1;
                } else (arr[right] < arr[right - 1] && arr[right - 1] > arr[right - 2]) {
                    dp[left][right] = dp[left][right - 1] + 1;
                } else {
                    dp[left][right] = 0;
                }
            }
        }

    }

}

public class Starter {
    public static void main(String[] args) {

        Assert.assertEquals(new Solution().maxTurbulenceSize(new int[] { 4, 8, 12, 16 }), 2);

        Assert.assertEquals(new Solution().maxTurbulenceSize(new int[] { 9, 4, 2, 10, 7, 8, 8, 1, 9 }), 5);
    }
}