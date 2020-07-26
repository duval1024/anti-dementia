package com.al.p139_单词拆分;

import java.awt.image.DataBufferFloat;
import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author duval
 * @date 2020-07-25 18:15
 */
class Solution {

//    public boolean wordBreak(String s, List<String> wordDict) {
//        if (s == null || s.length() == 0) {
//            return true;
//        }
//
//        for (String word : wordDict) {
//            if (s.startsWith(word)) {
//                String leftString = s.substring(word.length());
//                if(wordBreak(leftString, wordDict)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }


    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        Set<String> wordSet = new HashSet<>(wordDict);
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];
    }

    public boolean wordBreakPinchBack(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int maxLength = Integer.MIN_VALUE, minLength = Integer.MAX_VALUE;
        Set<String> wordSet = new HashSet<>(wordDict.size());
        for (String word : wordDict) {
            maxLength = Integer.max(maxLength, word.length());
            minLength = Integer.min(minLength, word.length());
            wordSet.add(word);
        }

        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - minLength; j >= 0 && (i - j <= maxLength); j--) {
                if (dp[j] && wordSet.contains(s.substring(j, i)) ) {
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];

    }

}
