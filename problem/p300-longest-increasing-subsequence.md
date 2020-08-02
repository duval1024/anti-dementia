# p300-最长上升子序列
## 题目来源
https://leetcode-cn.com/problems/longest-increasing-subsequence/
## 题目描述

给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:
```java
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
```
说明:

- 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
- 你算法的时间复杂度应该为 O(n2) 。
进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?

## 题目难度
- O(n2): ★★★
- O(n log n):★★★★★
## 核心知识
动态规划、贪心+二分
## 解题思路
### 动态规划
O(n)的解法相对容易想到：因为每一个长度为i的最长子串是从长度为i-1的最长子串的基础上，增加num[i]而构成。于是有状态转移方程:

$$
\begin{cases}
dp[0..n]=1, \\\\
dp[i]=max(dp[j]) + 1 \ \ (0<=j<i, \ \ num[i] > num[j])
\end{cases}
$$

于是得到O(n)解法：

```java

    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int dp[] = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Integer.max(dp[j] + 1, dp[i]);
                }
            }
            max = Integer.max(max, dp[i]);
        }

        return max;
    }

```

### 贪心+二分
这个解法不好想到，我也是看了题解才想明白。就不班门弄斧了，直接看[官方题解](https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/)吧。

题解中维护一个单调递增数组的思路非常有意思，值得借鉴学习。

我的ac代码如下：

```java
    public int lengthOfLISBinary(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int d[] = new int[nums.length];
        d[0] = nums[0];
        int maxLength = 1;
        for (int index = 1; index < nums.length; index++) {
            if (nums[index] > d[maxLength - 1]) {
                d[maxLength] = nums[index];
                maxLength++;
            } else {
                int l = 0;
                int r = maxLength - 1;

                while (l <= r) {
                    if (l == r) {
                        // 终止条件l==r
                        if (d[l] >= nums[index]) {
                            d[l] = nums[index];
                        } else {
                            d[l + 1] = nums[index];
                        }
                        break;
                    }

                    int mid = (l + r) / 2;
                    if (d[mid] < nums[index]) {
                        // 收缩左边界，且避免越界
                        l = (mid + 1 < r) ? mid + 1 : r;
                    } else {
                        // 收缩右边界，且避免越界
                        r = (mid - 1 > l) ? mid - 1 : l;
                    }
                }
            }
        }

        return maxLength;
    }

```

官方的AC代码更加简单，二分的时候目标是找到一个刚好比当前要插入的数字小的位置pos，如果找不到，则插入到最开头。

```java
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int len = 1, n = (int)nums.size();
        if (n == 0) return 0;
        vector<int> d(n + 1, 0);
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) d[++len] = nums[i];
            else{
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    }
                    else r = mid - 1;
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
};
```

## 其他补充
子序列相关问题：
- [p53-最大子序和](../problem/p53-maximum-subarray.md)
- [p300-最长上升子序列](../problem/p300-longest-increasing-subsequence.md)