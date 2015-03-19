package com.read.test.jdk.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by yfwangrui on 2015/3/6.
 */
public class InputStreamTest {

    @Test
    public void test() {
        InputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream("D:\\MyWorkSpace\\Test\\src\\main\\java\\com\\read\\test\\jdk\\io\\text.txt"));
            byte[] buff = new byte[Integer.MAX_VALUE >>> 8]; //如果是中文会出现问题，GBK是双字节，但是\n这些是单字节，
            // 所以，如果buff中出现单数的单字节字符，那么中文字符的两个字节就会被拆分一个出来，那么，
            //下一次再取的时候，中文字符的字节全部错位，造成乱码，建议用BufferedReader
            int result = input.read(buff);
            while (result != -1) {
                String buffStr = new String(buff, "GBK");
                System.out.print(buffStr);
                result = input.read(buff);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {



            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
