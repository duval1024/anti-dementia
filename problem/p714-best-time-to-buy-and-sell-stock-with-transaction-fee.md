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

贪心解法的其实就是人的正常思维：收益最大化，并且厌恶亏损。于是有思路：
- 令昨天股票成本为*cost*元；
- 如果今天股票价格*price[i]*比昨天成本*cost*高，那么昨天不应该卖出，如果今天卖出可以增加收益*profit=price[i] - cost - fee*；然后这部分收益应该落袋为安（比如被老婆搜刮充公)，那么当前股票成本为*cost = price[i] - fee*;
- 如果今天股价*price[i]*比昨天成本*cost*低了，那么昨天应该赶紧卖掉落袋为安，而今天应该买入，因为今天的股票成本更低为*cost=price[i] - fee*。

初始条件是第一天买入，也就是*cost=price[0]*，于是可以得到贪心解法：

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

### 动态规划解法

每天的价格变化数组price[N]构成第一维，此外每天都有持仓 *dp[i][1]* 和空仓 *dp[i][0]* 两种状态，因此得到2*N的状态转移方程：

$$ 
\begin{cases}
dp[0][0] = 0,\ dp[0][1] = -prices[0]; \\\\
dp[i][0]= max(dp[i - 1][0], \ dp[i - 1][1] + prices[i] - fee); \\\\
dp[i][1]= max(dp[i - 1][0] - prices[i], \ dp[i - 1][1]).
\end{cases}
$$

容易得到如下代码：

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

此外，状态方程只用到 *dp[i - 1][0]* 和 *dp[i - 1][1]*。因此可以进行状态压缩，此处不再赘言。

## 其他补充

股票买卖类型题目：
- [p121-买卖股票的最佳时机](p121-best-time-to-buy-and-sell-stock.md)
- [p122--买卖股票的最佳时机 II](p122-best-time-to-buy-and-sell-stock-ii.md)
- [p123-买卖股票的最佳时机 III](p123-best-time-to-buy-and-sell-stock-iii.md)
- [p188-买卖股票的最佳时机 IV](p188-best-time-to-buy-and-sell-stock-iv.md)
- [p714-买卖股票的最佳时机含手续费](p714-best-time-to-buy-and-sell-stock-with-transaction-fee.md)
- [p309-最佳买卖股票时机含冷冻期](p309_best-time-to-buy-and-sell-stock-with-cooldown.md)
