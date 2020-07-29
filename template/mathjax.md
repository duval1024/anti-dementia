## 空格
$$超长空格\qquad超长空格$$
$$长空格\quad长空格$$
$$短空格\ 短空格$$
## 常见公式
$$dp[i] = dp[j] \qquad (if \ s[j,i] \in WordDict) $$

## 公式块

$$
\begin{cases}
dp[0] = nums[0],  \\\\
dp[1] = max(nums[0], nums[1]), \\\\
dp[i]=max(dp[i-2] + nums[i], dp[i-1]) 
\end{cases}
$$