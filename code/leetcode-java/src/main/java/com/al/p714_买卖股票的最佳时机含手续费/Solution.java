package com.al.p714_买卖股票的最佳时机含手续费;

import java.util.Stack;

/**
 * @author duval
 * @date 2020-07-22 22:09
 */
class Solution {
//    public int maxProfit(int[] prices, int fee) {
//        int low = prices[0], high = prices[0];
//
//        int profit = 0;
//        for (int price : prices) {
//            if (price + fee < high) {
//                if (high - low > fee) {
//                    profit += (high - low - fee);
//                }
//                low = price;
//                high = low;
//            } else if (price > high) {
//                high = price;
//            } else if (price < low) {
//                low = price;
//                high = low;
//            }
//        }
//
//        if (high - low > fee) {
//            profit += (high - low - fee);
//        }
//        return profit;
//    }

    public int maxProfixGreedy(int[] prices, int fee) {
        if (prices.length < 2) {
            return 0;
        }

        int profit = 0, cost = prices[0];
        for (int i = 1; i < prices.length; i++) {
           if (prices[i] < cost) {
               cost = prices[i];
           } else if (prices[i] > cost + fee) {
               profit +=(prices[i] - cost - fee);
               cost = prices[i] - fee;
           }
        }

        return profit;
    }

    public int maxProfit(int[] prices, int fee) {
        int dp[][] = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Integer.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][0] = Integer.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
        }

        return Integer.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }

}
