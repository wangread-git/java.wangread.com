package com.read.test.algorithm.array;

public class StringNumberCalculate {

    /**
     * 滴滴面试题：字符串数字计算
     * 给出两个代表数字字符串a和b，计算a+b的结果，a与b的长度不限
     * 原答案
     */
    public String calculate(String a, String b) {
        int length = Math.max(a.length(), b.length()) + 1;
        byte[] bytesa = new byte[length];
        byte[] bytesb = new byte[length];
        for (int i = length - 1; i >= 0; i--) {
            if (i >= length - a.length()) {
                bytesa[i] = (byte) (a.charAt(i - length + a.length()) - '0');
            } else {
                bytesa[i] = 0;
            }
            if (i >= length - b.length()) {
                bytesb[i] = (byte) (b.charAt(i - length + b.length()) - '0');
            } else {
                bytesb[i] = 0;
            }
        }

        byte[] res = new byte[length];
        boolean increase = false;
        for (int i = length - 1; i > 0; i--) {
            int n = bytesa[i] + bytesb[i];
            if (increase) {
                n++;
            }
            if (n >= 10) {
                res[i] = (byte) (n - 10);
                increase = true;
            } else {
                res[i] = (byte) n;
                increase = false;
            }
        }
        byte[] str = new byte[length];
        int index = 0;
        boolean first = false;
        for (int i = 0; i < length; i++) {
            if (res[i] != 0 && !first) {
                first = true;
                index = i;
            }
            str[i] = (byte) (res[i] + '0');
        }
        return new String(str, index, length - index);
    }

    /**
     * 滴滴面试题：字符串数字计算
     * 给出两个代表数字字符串a和b，计算a+b的结果，a与b的长度不限
     * AI优化后答案
     */
    public String calculate2(String a, String b) {
        // 输入验证
        if (a == null || a.isEmpty()) a = "0";
        if (b == null || b.isEmpty()) b = "0";

        int maxLen = Math.max(a.length(), b.length());
        StringBuilder result = new StringBuilder();

        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        // 从右往左逐位相加
        while (i >= 0 || j >= 0 || carry > 0) {
            int digitA = (i >= 0) ? a.charAt(i) - '0' : 0;
            int digitB = (j >= 0) ? b.charAt(j) - '0' : 0;

            int sum = digitA + digitB + carry;
            result.append(sum % 10);
            carry = sum / 10;

            i--;
            j--;
        }

        // 反转结果（因为是从低位到高位构建的）
        return result.reverse().toString();
    }
}
