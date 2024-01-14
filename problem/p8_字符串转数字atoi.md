# 题目名字
## 题目描述

## 题目难度
> ★★★
## 核心知识
状态机、模拟

## 解题思路

直接模拟相对简单：

```go

func myAtoi(s string) int {
	s = strings.Trim(s, " ")
	var value int64
	var sign int64 = 1
	for index, ch := range s {
		if index == 0 && ch == '-' {
			sign = -1
		} else if index == 0 && ch == '+' {
			sign = 1
		} else if '0' <= ch && ch <= '9' {
			value = value*10 + int64(ch-'0')
			if sign == 1 {
				if value >= math.MaxInt32 {
					return math.MaxInt32
				}
			} else {
				if value * sign <= math.MinInt32 {
					return math.MinInt32
				}
			}
		} else {
			break
		}
	}
	return int(sign * value)
}


```

## 其他补充
