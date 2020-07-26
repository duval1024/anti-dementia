package com.al.p139_单词拆分;

import junit.framework.TestCase;
import org.junit.Assert;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author duval
 * @date 2020-07-25 18:21
 */
public class SolutionTest extends TestCase {

    public void testWordBreak() {
        Assert.assertEquals(new Solution().wordBreak("leetcode", Arrays.asList("leet", "code")), true);
        Assert.assertEquals(new Solution().wordBreak("applepenapple", Arrays.asList("apple", "pen")), true);
        Assert.assertEquals(new Solution().wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")), false);
        Assert.assertEquals(new Solution().wordBreak("cars", Arrays.asList("car", "ca", "rs")), true);
        Assert.assertEquals(new Solution().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")), false);

        Assert.assertEquals(new Solution().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "b")), true);


        Assert.assertEquals(new Solution().wordBreakPinchBack("leetcode", Arrays.asList("leet", "code")), true);
        Assert.assertEquals(new Solution().wordBreakPinchBack("applepenapple", Arrays.asList("apple", "pen")), true);
        Assert.assertEquals(new Solution().wordBreakPinchBack("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")), false);
        Assert.assertEquals(new Solution().wordBreakPinchBack("cars", Arrays.asList("car", "ca", "rs")), true);
        Assert.assertEquals(new Solution().wordBreakPinchBack("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")), false);

        Assert.assertEquals(new Solution().wordBreakPinchBack("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "b")), true);
    }
}