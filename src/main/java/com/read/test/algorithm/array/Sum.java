package com.read.test.algorithm.array;

public class Sum {

    /**
     * @see <a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/">两数之和</a>
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}
