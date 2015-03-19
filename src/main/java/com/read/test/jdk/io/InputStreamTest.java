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
            byte[] buff = new byte[Integer.MAX_VALUE >>> 8]; //��������Ļ�������⣬GBK��˫�ֽڣ�����\n��Щ�ǵ��ֽڣ�
            // ���ԣ����buff�г��ֵ����ĵ��ֽ��ַ�����ô�����ַ��������ֽھͻᱻ���һ����������ô��
            //��һ����ȡ��ʱ�������ַ����ֽ�ȫ����λ��������룬������BufferedReader
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
