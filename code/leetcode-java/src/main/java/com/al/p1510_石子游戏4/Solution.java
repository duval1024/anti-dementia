package com.al.p1510_石子游戏4;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-08-09 10:27
 */
public class Solution {

    private int state[];

    /**
     * @param current
     * @return -1表示必输、1表示稳赢
     */
    private int dfs(int current) {
        // 如果当前石子数为0，意味着当前选手已经输掉游戏
        if (current == 0) {
            return -1;
        }

        // 记忆化数组有记录，则直接返回
        if (state[current] != 0) {
            return state[current];
        }

        int step = 1;
        int result = 0;
        // 当前选手取走step*step个石子
        while (step * step <= current) {
            // result为后手的结果，当前选手的目标是令后手输掉
            result = dfs(current - step * step);
            // 因此，此处只要后手输了，则已经找到合适的step，可以终止循环
            if (result < 0) {
                break;
            }
            step++;
        }
        // 当前选手的输赢状态为-result，此处记忆化，并返回
        state[current] = -result;
        return -result;
    }


    public boolean winnerSquareGame(int n) {
        state = new int[n + 1];
        int result = dfs(n);

        switch (result) {
            case 1:
                return true;
            default:
                return false;
        }
    }


    public boolean winnerSquareGameDP(int n) {
        int[] dp = new int[n + 1];
        dp[n] = -1;
        for (int i = n - 1; i >= 0; i--) {
            for (int step = 1; i + step * step <= n; step++) {
                // 先手想尽办法令后手输
                if (dp[i + step * step] < 0) {
                    // 如果发现后手输掉游戏，则保存状态并终止循环
                    dp[i] = 1;
                    break;
                }
            }
            // 如果先手没有找到必赢办法，则输掉游戏
            dp[i] = dp[i] == 0 ? -1 : dp[i];
        }

        if (dp[0] < 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean winnerSquareGameDP1(int n) {
        boolean[] dp = new boolean[n + 1];
        dp[n] = false;
        for (int i = n - 1; i >= 0; i--) {
            for (int step = 1; i + step * step <= n; step++) {
                // 先手想尽办法令后手输
                dp[i] = dp[i] || !dp[i + step * step];
                if (dp[i]) {
                    break;
                }
            }
        }

        if (dp[0]) {
            return true;
        } else {
            return false;
        }
    }


    @Test
    public void test() {
        Assert.assertEquals(new Solution().winnerSquareGame(1), true);
        Assert.assertEquals(new Solution().winnerSquareGame(2), false);
        Assert.assertEquals(new Solution().winnerSquareGame(4), true);

        Assert.assertEquals(new Solution().winnerSquareGame(4), true);


        Assert.assertEquals(new Solution().winnerSquareGameDP(1), true);
        Assert.assertEquals(new Solution().winnerSquareGameDP(2), false);
        Assert.assertEquals(new Solution().winnerSquareGameDP(4), true);
        Assert.assertEquals(new Solution().winnerSquareGameDP(4), true);


        Assert.assertEquals(new Solution().winnerSquareGameDP1(1), true);
        Assert.assertEquals(new Solution().winnerSquareGameDP1(2), false);
        Assert.assertEquals(new Solution().winnerSquareGameDP1(4), true);
        Assert.assertEquals(new Solution().winnerSquareGameDP1(4), true);



    }
}
