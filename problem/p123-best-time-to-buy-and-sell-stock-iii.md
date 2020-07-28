# p123-买卖股票的最佳时机 III
## 题目难度
困难
## 题目来源
https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
## 题目描述

给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
```java
输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
```

示例 2:

```
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```

示例 3:
```
输入: [7,6,4,3,1] 
输出: 0 
解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
```

## 核心知识

动态规划

## 解题思路
股票系列里比较难的一道，周末用贪心来做没能AC全部样例。后来很容易就想到了三维DP，但没想到三维DP的边界这么复杂！
今天上班午休吃饭时候灵机一闪，想明白了边界问题 = = 。于是有以下的三维DP解法。
当然这道题还可以进行空间压缩，以及使用二维一维DP来解决。过几天我慢慢想慢慢续写。
先来看看三维DP解法：
### 三维DP
- 状态转移方程：
```java
    dp[i][k][0] = Integer.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
    dp[i][k][1] = Integer.max(dp[i - 1][k - 1][0] - prices[i], dp[i - 1][k][1]);
```
第一维的i表示第i天(0 <= i < n)；
第二维的k表示买了k次（0 <= k <= 2)；
第三维的0表示空仓，1表示持仓。

- 边界条件比较复杂
1. 买入次数k为0却有持仓 ：dp[i][0][1] = Integer.MIN_VALUE / 2;
2. 第0天买入了两次：dp[0][2][0] = dp[0][2][1] = Integer.MIN_VALUE / 2;
3. 第0天买入了1次且有持仓：dp[0][1][1] = -prices[0];
4. 其他情况都是初始值0.

> 需要注意边界值不能设置为无穷小Integer.MIN_VALUE，否则状态转移的时候减去prices[i]会溢出为正数。

- 最优结果
取以下两者最优：
1. 完成两次买卖且空仓： dp[n - 1][2][0]
2. 完成一次买卖且空仓： dp[n-1][1][0]

```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int dp[][][] = new int[n][3][2];
        for (int i = 0; i < n; i++) {
            dp[i][0][1] = Integer.MIN_VALUE / 2;
        }

        dp[0][2][0] = Integer.MIN_VALUE / 2;
        dp[0][2][1] = Integer.MIN_VALUE / 2;
        dp[0][1][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[i][k][0] = Integer.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Integer.max(dp[i - 1][k - 1][0] - prices[i], dp[i - 1][k][1]);
            }
        }

        return Integer.max(dp[n - 1][2][0], dp[n-1][1][0]);
    }
}
```
### 三维DP+空间压缩


### 二维DP+空间压缩


### 一维DP+空间压缩

## 其他补充
股票买卖类型题目：
- [p121-买卖股票的最佳时机](p121-best-time-to-buy-and-sell-stock.md)
- [p122--买卖股票的最佳时机 II](p122-best-time-to-buy-and-sell-stock-ii.md)
- [p123-买卖股票的最佳时机 III](p123-best-time-to-buy-and-sell-stock-iii.md)
- [p714-买卖股票的最佳时机含手续费](p714-best-time-to-buy-and-sell-stock-with-transaction-fee.md)
- [p309-最佳买卖股票时机含冷冻期](p309_best-time-to-buy-and-sell-stock-with-cooldown.md)