package com.al.p3_Longest_Substring_Without_Repeating_Characters;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duval
 * @date 2019-10-13 13:30
 */
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int length = s.length();
        int max = 0;
        Map<Character, Integer> set = new HashMap<>();

        while (left < length && right < length) {
            if (!set.containsKey(s.charAt(right))) {
                set.put(s.charAt(right), right);
                right++;
                max = Math.max(max, right - left);
                continue;
            }

            int newLeft = set.get(s.charAt(right));
            for (int i = left; i <= newLeft; i++) {
                set.remove(s.charAt(i));
            }
            left = newLeft + 1;
        }

        return max;
    }

    @Test
    public void test() {
        assert lengthOfLongestSubstring("abcdefg") == 7;
        assert lengthOfLongestSubstring("abcabcbb") == 3;
        assert lengthOfLongestSubstring("bbbbb") == 1;
        assert lengthOfLongestSubstring("pwwkew") == 3;
        assert lengthOfLongestSubstring("") == 0;
        assert lengthOfLongestSubstring("wwcceedd") == 2;
    }
}
