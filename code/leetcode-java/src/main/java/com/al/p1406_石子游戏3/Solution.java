package com.al.p1406_石子游戏3;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-08-09 08:25
 */
public class Solution {

    private int[] memo;

    private int dfs(int[] stoneValues, int index) {
        if (index >= stoneValues.length) {
            return 0;
        }

        if (memo[index] != Integer.MIN_VALUE) {
            return memo[index];
        }

        int max = Integer.MIN_VALUE;
        int step = 1;
        while (step <= 3) {
            max = Integer.max(max, stoneValues[index] - dfs(stoneValues, index + step));
            step++;
        }

        memo[index] = max;

        return max;
    }


    public String stoneGameIII(int[] stoneValue) {
        for (int i = stoneValue.length - 2; 0 <= i; i--) {
            stoneValue[i] += stoneValue[i + 1];
        }

        this.memo = new int[stoneValue.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Integer.MIN_VALUE;
        }

        int aliceValue = dfs(stoneValue, 0);
        int bobValue = stoneValue[0] - aliceValue;
        if (aliceValue > bobValue) {
            return "Alice";
        } else if (aliceValue == bobValue) {
            return "Tie";
        } else {
            return "Bob";
        }
    }


//    public String stoneGameIIIDP(int[] stoneValue) {
//        int n = stoneValue.length;
//        int dp[] = new int[n];
//
//        for (int i = n - 2; i >= 0; i--) {
//            stoneValue[i] += stoneValue[i + 1];
//        }
//
//        for (int i = n - 1; i >= 0; i--) {
//            dp[i] = Integer.MIN_VALUE;
//            for (int x = 1; x <= 3; x++) {
//                if (i + x >= n) {
//                    dp[i] = Integer.max(dp[i], stoneValue[i]);
//                } else {
//                    dp[i] = Integer.max(dp[i], stoneValue[i] - dp[i + x]);
//                }
//            }
//        }
//
//
//        if (dp[0] * 2 == stoneValue[0]) {
//            return "Tie";
//        } else if (dp[0] * 2 < stoneValue[0]) {
//            return "Bob";
//        } else {
//            return "Alice";
//        }
//    }

    public String stoneGameIIIDP(int[] stoneValue) {
        int n = stoneValue.length;
        int dp[] = new int[n + 1];

        for (int i = n - 2; i >= 0; i--) {
            stoneValue[i] += stoneValue[i + 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            for (int x = 1; x <= 3 && i + x <= n; x++) {
                dp[i] = Integer.max(dp[i], stoneValue[i] - dp[i + x]);
            }
        }


        if (dp[0] * 2 == stoneValue[0]) {
            return "Tie";
        } else if (dp[0] * 2 < stoneValue[0]) {
            return "Bob";
        } else {
            return "Alice";
        }
    }


    @Test
    public void test() {
        Assert.assertEquals(new Solution().stoneGameIII(new int[]{1, 2, 3, 7}), "Bob");
        Assert.assertEquals(new Solution().stoneGameIII(new int[]{1, 2, 3, -9}), "Alice");
        Assert.assertEquals(new Solution().stoneGameIII(new int[]{1, 2, 3, -1, -2, -3, 7}), "Alice");
        Assert.assertEquals(new Solution().stoneGameIII(new int[]{1, 2, 3, 6}), "Tie");
        Assert.assertEquals(new Solution().stoneGameIII(new int[]{-1, -2, -3}), "Tie");

        Assert.assertEquals(new Solution().stoneGameIIIDP(new int[]{1, 2, 3, 7}), "Bob");
        Assert.assertEquals(new Solution().stoneGameIIIDP(new int[]{1, 2, 3, -9}), "Alice");
        Assert.assertEquals(new Solution().stoneGameIIIDP(new int[]{1, 2, 3, -1, -2, -3, 7}), "Alice");
        Assert.assertEquals(new Solution().stoneGameIIIDP(new int[]{1, 2, 3, 6}), "Tie");
        Assert.assertEquals(new Solution().stoneGameIIIDP(new int[]{-1, -2, -3}), "Tie");

    }
}
