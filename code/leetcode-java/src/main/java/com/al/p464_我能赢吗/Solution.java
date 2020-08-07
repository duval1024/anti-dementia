package com.al.p464_我能赢吗;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-08-02 22:30
 */
public class Solution {

    private int desiredTotal;

    private int maxChoosableInteger;

    private boolean[][] dp;

    boolean visited(int currentValue, int expected) {
        return (currentValue & ( 1 << (expected - 1))) > 0;
    }

    /**
     * 深度搜索返回输赢
     *
     * @param currentValue
     * @param order
     * @return
     */
    private boolean dfs(int currentValue, int order) {
        if (currentValue > desiredTotal) {
            return order == 0 ? true : false;
        }

        if (dp[currentValue][order]) {
            return dp[currentValue][order];
        }


        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (!visited(currentValue, i)) {
                int nextValue = currentValue | (1 << (i - 1));
                int nextOrder = (order + 1) % 2;

                if (dfs(nextValue, nextOrder)) {
                    if (nextValue <= desiredTotal) {
                        dp[nextValue][nextOrder] = true;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }


    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        this.desiredTotal = desiredTotal;
        this.maxChoosableInteger = maxChoosableInteger;
        this.dp = new boolean[desiredTotal + 1][2];

        return dfs(0, 0);
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().canIWin(10, 11), false);
        Assert.assertEquals(new Solution().canIWin(10, 8), true);

    }


}
