use std::collections::HashMap;

#[cfg(test)]
mod tests {
    use crate::Solution;

    #[test]
    fn it_works() {
        assert_eq!(Solution::subarray_sum(vec![1, 1, 1], 2), 2);
        assert_eq!(Solution::subarray_sum(vec![1, 1, 1, 1, 1], 3), 3);
        assert_eq!(Solution::subarray_sum(vec![1, 2, 3], 3), 2);


        assert_eq!(Solution::subarray_sum_by_map(vec![1, 1, 1], 2), 2);
        assert_eq!(Solution::subarray_sum_by_map(vec![1, 1, 1, 1, 1], 3), 3);
        assert_eq!(Solution::subarray_sum_by_map(vec![1, 2, 3], 3), 2);
    }
}

struct Solution {}

impl Solution {
    pub fn subarray_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mut sum = Vec::new();
        // 构建前缀和数组
        for i in 0..nums.len() {
            if i == 0 {
                sum.push(nums[i]);
            } else {
                sum.push(nums[i] + sum[i - 1]);
            }
        }

        // 暴力遍历
        let mut count = 0;
        for i in 0..sum.len() {
            let left_sum = if i == 0 { 0 } else { sum[i - 1] };
            for j in i..sum.len() {
                if sum[j] - left_sum == k {
                    count = count + 1;
                }
            }
        }

        count
    }

    pub fn subarray_sum_by_map(nums: Vec<i32>, k: i32) -> i32 {
        let mut count = 0;
        let mut sum_map = HashMap::new();

        let mut sum = 0;
        sum_map.insert(0, 1);
        for num in &nums {
            sum += num;
            count += (sum_map.get(&(sum - k)).unwrap_or(&0));

            if let Some(current) = sum_map.get_mut(&sum) {
                *current += 1;
            } else {
                sum_map.insert(sum, 1);
            }
        }

        count
    }
}
