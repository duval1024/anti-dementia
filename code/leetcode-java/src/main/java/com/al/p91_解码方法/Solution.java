package com.al.p91_解码方法;


/**
 * @author duval
 * @date 2020-07-23 22:20
 */
public class Solution {

    /**
     <pre>
        边界条件:
            1.s以'0'开头，返回0；
        dp状态方程:
            dp[0] = dp[1] = 1;
            1. 当s[i] == '0' :
                -> 当s[i-1] == '1' or '2', dp[i] = dp[i-2];
                -> 其他情况不满足编码要求，返回0;
            2. 当'1' <= s[i] <= '6':
                -> 当s[i-1] == '1' or '2', dp[i] = dp[i-1] + dp[i-2];
                -> 其他情况，dp[i] = dp[i-1];
            3. 其他情况 s[i] == '7' ~ '9':
                -> 当 s[i-1] = 1, dp[i] = dp[i-1] + dp[i-2];
                -> 其他情况, dp[i] = dp[i-1].
     </pre>
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int length = s.length();
        if (s.startsWith("0")) {
            return 0;
        }

        int pre = 1;
        int sub = 1;
        for (int index = 1; index < length; index++) {
            int next;
            int currentChar = s.charAt(index);
            int preChar = s.charAt(index - 1);
            if (currentChar == '0') {
                if (preChar == '1' || preChar == '2') {
                    next = pre;
                } else {
                    return 0;
                }
            } else if ('1' <= currentChar && currentChar <= '6') {
                if (preChar == '1' || preChar == '2') {
                    next = pre + sub;
                } else {
                    next = sub;
                }
            } else {
                if (preChar == '1') {
                    next = pre + sub;
                } else {
                    next = sub;
                }
            }

            pre = sub;
            sub = next;
        }

        return sub;
    }

    /**
     <pre>
     边界条件:
        1.s以'0'开头，返回0；
     dp状态方程:
        dp[0] = dp[1] = 1;
        1. 当s[i] == '0' :
            -> 当s[i-1] == '1' or '2', dp[i] = dp[i-2];
            -> 否则不满足编码要求，返回0;
        2. 当'1' <= s[i] <= '6' ， s[i-1] == '1' or '2':
            -> dp[i] = dp[i-1] + dp[i-2];
        3. 当s[i] == '7' ~ '9'，  s[i-1] = 1:
            -> dp[i] = dp[i-1] + dp[i-2];
        4. 其他情况, dp[i] = dp[i-1].
     </pre>
     * @param s
     * @return
     */
    public int numDecodingsSimplify(String s) {
        if (s.startsWith("0")) {
            return 0;
        }

        int pre = 1;
        int sub = 1;
        for (int index = 1; index < s.length(); index++) {
            int next = sub;
            int currentChar = s.charAt(index);
            int preChar = s.charAt(index - 1);
            if (currentChar == '0') {
                if (preChar == '1' || preChar == '2') {
                    next = pre;
                } else {
                    return 0;
                }
            } else if (('1' <= currentChar && currentChar <= '6') && (preChar == '1' || preChar == '2')) {
                next = pre + sub;
            } else if (('7' <= currentChar && currentChar <= '9') && preChar == '1') {
                next = pre + sub;
            }

            pre = sub;
            sub = next;
        }

        return sub;
    }
}
