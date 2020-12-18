package com.al.p5_最长回文子串;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2020-12-17 16:01
 */

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        String longestString = "";
        for (int left = 0; left < s.length() - 1; left++) {
            String tempString = doFind(s, left, left);
            if (longestString.length() < tempString.length()) {
                longestString = tempString;
            }

            if (s.charAt(left) != s.charAt(left + 1)) {
                continue;
            }

            tempString = doFind(s, left, left + 1);

            if (longestString.length() < tempString.length()) {
                longestString = tempString;
            }
        }

        return longestString;
    }

    public String doFind(String s, int left, int right) {
        int maxLength = s.length();

        while (true) {
            boolean hasNext = (left - 1 >= 0) && (right + 1 < maxLength) && (s.charAt(left - 1) == s.charAt(right + 1));
            if (hasNext) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return s.substring(left, right + 1);
    }
}

class DpSolution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int n = s.length();
        boolean dp[][] = new boolean[n][n];

        String longestString = "";
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = true;
                }
                if (len == 2 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                }

                if (len > 2 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                }

                if (dp[i][j] && j - i + 1 > longestString.length()) {
                    longestString = s.substring(i, j + 1);
                }
            }
        }

        return longestString;
    }

}

public class Starter {

    public static void main(String[] args) {
        Assert.assertEquals(new Solution().longestPalindrome("bb"), "bb");
        Assert.assertEquals(new Solution().longestPalindrome("a"), "a");
        Assert.assertEquals(new Solution().longestPalindrome("bbb"), "bbb");
        Assert.assertEquals(new Solution().longestPalindrome("abbb"), "bbb");
        Assert.assertEquals(new Solution().longestPalindrome("abbbdd"), "bbb");


        Assert.assertEquals(new DpSolution().longestPalindrome("bb"), "bb");
        Assert.assertEquals(new DpSolution().longestPalindrome("a"), "a");
        Assert.assertEquals(new DpSolution().longestPalindrome("bbb"), "bbb");
        Assert.assertEquals(new DpSolution().longestPalindrome("abbb"), "bbb");
        Assert.assertEquals(new DpSolution().longestPalindrome("abbbdd"), "bbb");

    }
}
