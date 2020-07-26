# p139-单词拆分
## 题目来源
https://leetcode-cn.com/problems/word-break/

## 题目描述

给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：
- 拆分时可以重复使用字典中的单词。
- 你可以假设字典中没有重复的单词。
示例 1：
```text
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
```
示例 2：
```text
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
```
示例 3：
```text
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
```

## 核心知识
深度优先搜索、宽度优先搜索、动态规划、剪枝优化

## 解题思路

### 深度优先搜索
最先想到的是用DFS来解决，于是可以迅速写出以下解法：
```java
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                String leftString = s.substring(word.length());
                if(wordBreak(leftString, wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }
```
非常遗憾，单纯的DBF会运行超时，代码只通过了36个用例中的29个。但至少说明这个思路是对的。不能通过的样例有如：

```
"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
```

显而易见，这种数据会导致深度搜索的分支数量非常发散，大量的回溯导致运行超时。

此处需要引入记忆化搜索，减少回溯。（待续）
  


### 动态规划

很容易想出状态转移方程：

$$dp[i] = dp[j] \qquad (if \ s[j,i] \in WordDict) $$

代码如下：
```java
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
```
这种解法有几个低效的地方:
- 1. 内循环j从低位开始遍历，可能遍历很久才能找到符合的子串。因此应该逆序遍历；
- 2. j的遍历范围[0, i)过大，做了很多无用功，实际有效遍历范围为：[i - 最小字符串长, i - 最大字符串长度]。因此可以剪枝。

优化后可以击败95%，优化后的代码如下：

```java
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
```

## 其他补充
