package com.read.test.algorithm.array;

public class Reverse {

    /**
     * @see <a href="https://leetcode.cn/problems/reverse-string/description/">反转字符串</a>
     */
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
