package com.al.p1414_和为K的最少斐波那契数字数目;

import java.util.ArrayList;

/**
 * @author duval
 * @date 2020-07-21 22:43
 */
public class Solution {

    public int findMinFibonacciNumbers(int k) {
        ArrayList<Integer> fibonachi = new ArrayList<>(45);
        int first = 1, second = 1;
        while (first <= k) {
            fibonachi.add(first);
            int temp = first + second;
            first = second;
            second = temp;
        }

        int count = 0;
        for (int i = fibonachi.size() - 1; i >= 0 && k != 0; i--) {
            if (fibonachi.get(i) <= k) {
                k -= fibonachi.get(i);
                count++;
            }
        }
        return count;
    }

}
