# p121-买卖股票的最佳时机
## 题目来源
https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/

## 题目描述
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。

注意：你不能在买入股票前卖出股票。

示例 1:
```text
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
```
示例 2:
```text
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

## 核心知识

## 解题思路

这题太简单了，为了股票问题系列的完整而记录下来。算法思路的核心就是保存历史的最小价格minPrice，并且记录最大利润maxProfit。

```java
  public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int index = 1; index < prices.length; index++) {
            if (prices[index] < minPrice) {
                minPrice = prices[index];
            } else {
                maxProfit = Integer.max(prices[index] - minPrice, maxProfit);
            }
        }

        return maxProfit;
    }
```

## 其他补充


股票买卖类型题目：
- [p121-买卖股票的最佳时机](p121-best-time-to-buy-and-sell-stock.md)
- [p122--买卖股票的最佳时机 II](p122-best-time-to-buy-and-sell-stock-ii.md)
- [p123-买卖股票的最佳时机 III](p123-best-time-to-buy-and-sell-stock-iii.md)
- [p188-买卖股票的最佳时机 IV](p188-best-time-to-buy-and-sell-stock-iv.md)
- [p714-买卖股票的最佳时机含手续费](p714-best-time-to-buy-and-sell-stock-with-transaction-fee.md)
- [p309-最佳买卖股票时机含冷冻期](p309_best-time-to-buy-and-sell-stock-with-cooldown.md)
