## 空格
$$超长空格\qquad超长空格$$
$$长空格\quad长空格$$
$$短空格\ 短空格$$
## 常见公式
$$dp[i] = dp[j] \qquad (if \ s[j,i] \in WordDict) $$

## 公式块
$$ 
\begin{cases}
dp[i][0]= max(dp[i - 1][1] , dp[i - 1][0]) \\\\
dp[i][1]= dp[i - 1][0] + nums[i]
\end{cases}
$$