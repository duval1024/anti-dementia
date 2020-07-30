# p188-买卖股票的最佳时机 IV
## 题目来源
https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
## 题目描述

给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
```text
输入: [2,4,1], k = 2
输出: 2
解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
```
示例 2:
```text
输入: [3,2,6,5,0,3], k = 2
输出: 7
解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
```
## 核心知识
动态规划、状态压缩
## 解题思路
这道题其实是[p123-买卖股票的最佳时机 III](./p123-best-time-to-buy-and-sell-stock-iii.md)的泛化。这里的买卖次数K可以是任意次。所以状态转移方程是一致的思路：

$$
\begin{cases}
dp[0][1..k][1] = -prices[0]; \\\\
dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]); \\\\
dp[i][k][1] = max(dp[i - 1][k - 1][0] - prices[i], dp[i - 1][k][1]).
\end{cases}
$$

很容易就写出以下代码：

```java
    public int maxProfit(int k, int[] prices) {
           if (prices == null || prices.length <= 1) {
                return 0;
            }

        int n = prices.length;
        int dp[][][] = new int[n][k + 1][2];
        for (int i = 1; i < k + 1; i++) {
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Integer.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Integer.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
            }
        }

        int max = 0;
        for (int i = 1; i < k + 1; i++) {
            max = Integer.max(max, dp[n - 1][i][0]);
        }

        return max;
    }
```

然而非常遗憾，211个样例只通过了209个，剩下的都是非常庞大的输入用例，执行结果都是超出了内存限制。显然，这里需要进行空间压缩。

首先想到的是i只用到i-1的数据，那么其实只需要dp[2][k+1][2]的空间就够了，于是有如下压缩：

```java
    public int maxProfitCompress(int k, int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int dp[][][] = new int[2][k + 1][2];
        for (int i = 1; i < k + 1; i++) {
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i % 2][j][0] = Integer.max(dp[(i - 1) % 2][j][0], dp[(i - 1) % 2][j][1] + prices[i]);
                dp[i % 2][j][1] = Integer.max(dp[(i - 1) % 2][j - 1][0] - prices[i], dp[(i - 1) % 2][j][1]);
            }
        }

        int max = 0;
        for (int i = 1; i < k + 1; i++) {
            max = Integer.max(max, dp[(n - 1) % 2][i][0]);
        }

        return max;
    }   

```

这个解法的空间复杂度是O(4*(k+1))，没想到提交后还是超出内存限制!!! 于是，想想下估计要压缩到二维才行。于是祭出以下解法：


```java
    public int maxProfitCompress1(int k, int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int dp[][] = new int[k + 1][2];
        for (int i = 1; i < k + 1; i++) {
            dp[i][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k + 1; j++) {
                dp[j][0] = Integer.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Integer.max(dp[j - 1][0] - prices[i],  dp[j][1]);
            }
        }

        int max = 0;
        for (int i = 1; i < k + 1; i++) {
            max = Integer.max(max, dp[i][0]);
        }

        return max;
    }
```

这思路就是把第一维拿掉，然后直接在二维数组上累算，空间复杂度降低到了一半到O(2*(K+1))。没想到还是超出内存限制！！！难道还要降低空间复杂度吗？？？

## 其他补充


