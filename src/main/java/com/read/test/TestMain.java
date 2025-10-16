package com.read.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yfwangrui on 2014/12/10.
 */
public class TestMain {

    @Test
    public void test() {
        String a = "109";
        String b = "1";
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
        System.out.println(new String(str, index, length - index));
    }
}
