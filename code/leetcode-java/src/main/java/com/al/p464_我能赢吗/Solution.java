package com.al.p464_我能赢吗;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.image.VolatileImage;

/**
 * @author duval
 * @date 2020-08-02 22:30
 */
public class Solution {

    private int maxChoosableInteger;

    private Boolean[] memo;

    /**
     * 深度搜索返回输赢
     *
     * @return
     */
    private boolean dfs(int desiredTotal, int visited) {
        // 如果desiredTotal小于0，则当前选手已经失败
        if (desiredTotal < 0) {
            return false;
        }

        // 如果有记忆化结果，则直接返回
        if (memo[visited] != null) {
            return memo[visited];
        }

        // 遍历所有可能
        for (int num = 1; num <= maxChoosableInteger; num++) {
            // 如果已经被取，则跳过
            if ((visited & (1 << (num - 1))) > 0) {
                continue;
            }

            // 如果发现当前选手已经赢了，或者是后手失败了，则已经找到当前选手稳赢走法，则终止循环并返回true
            if (desiredTotal - num == 0 || !dfs(desiredTotal - num, visited | (1 << (num - 1)))) {
                return memo[visited] = true;
            }
        }

        // 没有找到稳赢走法，则当前选手失败了
        return memo[visited] = false;
    }


    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // 总和不能达到desiredTotal，必输
        if ((1 + maxChoosableInteger) / 2 < desiredTotal) {
            return false;
        }

        this.maxChoosableInteger = maxChoosableInteger;
        // 初始化记忆化数组，状态空间复杂度为O(2^n)
        this.memo = new Boolean[1 << maxChoosableInteger];

        return dfs(desiredTotal, 0);
    }

    @Test
    public void test() {
        Assert.assertEquals(new Solution().canIWin(10, 11), false);
        Assert.assertEquals(new Solution().canIWin(10, 8), true);
        Assert.assertEquals(new Solution().canIWin(10, 0), true);
        Assert.assertEquals(new Solution().canIWin(5, 50), false);


    }


}
