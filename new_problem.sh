problem_name=$1

## 新建题解页
cp ./template/problem-template.md ./problem/$1.md
sed -i "s/题目名字/$1/g" ./problem/$1.md

## 新建资源目录
mkdir -p "./asset/$1"
