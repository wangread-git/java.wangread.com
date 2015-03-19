package com.read.test.jdk.io;

import org.junit.Test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by yfwangrui on 2015/3/5.
 */
public class PipeLineTest {

    @Test
    public void test() {
        try {
            final PipedOutputStream out = new PipedOutputStream();
            final PipedInputStream in = new PipedInputStream(out);

            Thread writeThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        out.write("hello world".getBytes());
                        Thread.sleep(10000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread readThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        int data = in.read();
                        while (data != -1) {
                            System.out.print((char) data);
                            data = in.read();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            writeThread.start();
            readThread.start();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
