# p6_z型变换
## 题目描述

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
