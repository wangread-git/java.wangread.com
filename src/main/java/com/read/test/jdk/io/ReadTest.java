package com.read.test.jdk.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yfwangrui on 2015/3/6.
 */
public class ReadTest {

    @Test
    public void test() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("D:\\MyWorkSpace\\Test\\src\\main\\java\\com\\read\\test\\jdk\\io\\text.txt"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println(sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testFileReader() {
        try {
            FileReader reader1 = new FileReader("C:\\Users\\Administrator\\Desktop\\functionId.txt");
            FileReader reader2 = new FileReader("C:\\Users\\Administrator\\Desktop\\functionId2.txt");

            BufferedReader br1 = new BufferedReader(reader1);
            BufferedReader br2 = new BufferedReader(reader2);

            Set<String> functionIdSet = new HashSet<String>();
            Set<String> functionId2Set = new HashSet<String>();

            String line;
            while ((line = br1.readLine()) != null) {
                functionIdSet.add(line);
            }

            while ((line = br2.readLine()) != null) {
                functionId2Set.add(line);
            }

            for (String functionId : functionIdSet) {
                if (!functionId2Set.contains(functionId)) {
                    System.out.println(functionId);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
