package com.al.p1654_到家的最少跳跃次数;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import junit.framework.Assert;

class Solution {
    /**
     * 
     * @param forbidden 禁止跳入的点
     * @param a         向前跳a步（a<=2000)
     * @param b         向后跳b步，不能连续跳两次（b<=2000)
     * @param x         家的位置
     * @return
     */
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> forbiddenSet = new HashSet<>();
        for (int num : forbidden) {
            forbiddenSet.add(num);
        }

        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(0, 0, false));

        int minimumJumps = -1;
        while (!queue.isEmpty()) {
            Status next = queue.poll();
            int currentIndex = next.index;
            int currentJump = next.jump;
            boolean back = next.back;
            // 已经到达x位置，说明已经是最优解
            if (currentIndex == x) {
                minimumJumps = currentJump;
                break;
            }
            // 向前跳需要满足两个条件
            // 1. currentIndex + a 没有被禁用；
            // 2. currentIndex + a 没有越界（根据条件推算得到6000为最大边界）。
            if (!forbiddenSet.contains(currentIndex + a) && currentIndex + a < 6000) {
                // 向前跳就能先到达的点一定是最优解（因为总比跳过头再回来要更优）
                forbiddenSet.add(currentIndex + a);
                queue.add(new Status(currentIndex + a, currentJump + 1, false));
            }
            // 向后跳需要满足三个条件
            // 1.当前状态不是向后跳产生的，因为不能连续向后跳两次；
            // 2.currentIndex - b 没有被禁用；
            // 3.currentIndex - b 没有越界。
            if (!back && !forbiddenSet.contains(currentIndex - b) && currentIndex - b >= 0) {
                queue.add(new Status(currentIndex - b, currentJump + 1, true));
            }
        }

        return minimumJumps;
    }

    /**
     * 当前状态
     */
    class Status {
        /** 当前下标 */
        public int index;
        /** 当前状态是否是向后跳产生的 */
        public boolean back;
        /** 跳跃次数 */
        public int jump;

        public Status(int index, int jump, boolean back) {
            this.index = index;
            this.back = back;
            this.jump = jump;
        }
    }

}

public class Starter {

    public static void main(String[] args) {

        Assert.assertEquals(new Solution().minimumJumps(new int[] { 8, 3, 16, 6, 12, 20 }, 15, 13, 11), -1);

        Assert.assertEquals(new Solution().minimumJumps(new int[] { 14, 4, 18, 1, 15 }, 3, 15, 9), 3);

        Assert.assertEquals(new Solution().minimumJumps(new int[] { 1, 6, 2, 14, 5, 17, 4 }, 16, 9, 7), 2);
    }
}