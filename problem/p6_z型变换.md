# p6_z型变换
## 题目描述
将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

P   A   H   N
A P L S I I G
Y   I   R
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
 

示例 1：

输入：s = "PAYPALISHIRING", numRows = 3
输出："PAHNAPLSIIGYIR"

示例 2：
输入：s = "PAYPALISHIRING", numRows = 4
输出："PINALSIGYAHRPI"
解释：
P     I    N
A   L S  I G
Y A   H R
P     I

示例 3：

输入：s = "A", numRows = 1
输出："A"
 

提示：

1 <= s.length <= 1000
s 由英文字母（小写和大写）、',' 和 '.' 组成
1 <= numRows <= 1000

## 题目难度
> ★★★★
## 核心知识
二维数组

## 解题思路
观察Z型变换的本质是在二维数组上填字符，然后按行读出非空字符；
还需要发现Z型变换每隔2*numRow-2个字符就会出现一次循环，每次循环都是先向下扩展numRow个字符，再向右上角扩展 numRow-2个字符，然后进入下一个循环；
再思考一下压缩掉非空字符，则有解：

```go
func convert(s string, numRows int) string {
    if numRows == 1 || len(s) <= numRows {
        return s
    }

    result := make([][]byte, numRows) 
    current := 0
    r := 2 * numRows - 2
    for index, ch := range []byte(s) {
        if index % r < numRows - 1 {
            result[current] = append(result[current], ch)
            current++
        } else {
            result[current] = append(result[current], ch)
            current--
        }
    }

    return string(bytes.Join(result, nil))

}

```

## 其他补充
