problem_name=$1

# 定义sed -i 参数(数组)
# Default case for Linux sed, just use "-i"
sedi=(-i)
case "$(uname)" in
  # For macOS, use two parameters
  Darwin*) sedi=(-i "")
esac	

########

## 新建题解页
cp ./template/problem-template.md ./problem/$1.md

## 修改标题
#sed -i "s/题目名字/$1/g" $1.md
sed "${sedi[@]}" "s/题目名字/$1/g" ./problem/$1.md

## 新建资源目录
mkdir -p "./asset/$1"
