# p714-买卖股票的最佳时机含手续费

## 题目来源

[https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee)

## 题目描述

给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

返回获得利润的最大值。

注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

示例 1:
```text
    输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
    输出: 8
    解释: 能够达到的最大利润:  
    在此处买入 prices[0] = 1
    在此处卖出 prices[3] = 8
    在此处买入 prices[4] = 4
    在此处卖出 prices[5] = 9
    总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
```
注意:
```text
    0 < prices.length <= 50000.
    0 < prices[i] < 50000.
    0 <= fee < 50000.
```

## 核心知识

贪心思想、动态规划（DP\)

## 解题思路

### 贪心解法

### 动态规划解法

## 通过代码

* 贪心

```java
    public int maxProfitGreedy(int[] prices, int fee) {
        if (prices.length < 2) {
            return 0;
        }
        // profit表示止盈额，cost表示成本价
        int profit = 0, cost = prices[0];
        for (int i = 1; i < prices.length; i++) {
           if (prices[i] < cost) {
               // 当前价格比成本价低，则应该买入新股
               cost = prices[i];
           } else if (prices[i] > cost + fee) {
               // 当前价格比成本价高（考虑成本），则应该及时止盈，并更新成本价
               profit +=(prices[i] - cost - fee);
               cost = prices[i] - fee;
           }
        }

        return profit;
    }
```

* 动态规划

```java
    public int maxProfit(int[] prices, int fee) {
        int dp[][] = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Integer.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][0] = Integer.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
        }

        return Integer.max(dp[prices.length-1][0], dp[prices.length-1][1]);
    }
```

## 其他补充

