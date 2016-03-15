package com.read.test.jdk.nio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yfwangrui on 2015/6/1.
 * <p/>
 * nio test
 */
public class NIOTest {

    @Test
    public void testChannelRead() {
        RandomAccessFile file = null;
        FileChannel fileChannel = null;
        try {
            file = new RandomAccessFile("D:\\MyWorkSpace\\GitHub\\java.wangread.com\\src\\main\\java\\com\\read\\test\\jdk\\io\\text.txt", "rw");

            fileChannel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(2046);
            int byteRead = fileChannel.read(byteBuffer);
            List<Byte> byteList = new ArrayList<Byte>();
            while (byteRead != -1) {

                byteBuffer.flip();

                while (byteBuffer.hasRemaining()) {
                    byteList.add(byteBuffer.get());
                }

                byteBuffer.clear();
                byteRead = fileChannel.read(byteBuffer);
            }

            byte[] bytes1 = new byte[byteList.size()];
            for (int i = 0; i < byteList.size(); i++) {
                bytes1[i] = byteList.get(i);
            }
            System.out.println(new String(bytes1, "GBK"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testSelector() {

    }

    @Test
    public void testSocketAccept() {
        ServerSocketChannel ssc = null;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        executor.allowCoreThreadTimeOut(true);
        try {
            ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(8000));
            //ServerSocketChannel设置为非阻塞之后，如果当前没有连接到达，accept会直接返回null
            ssc.configureBlocking(false);
            while (true) {
                SocketChannel sc = ssc.accept();
                if (sc != null) {
                    executor.submit(new SocketProcessor(sc));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ssc != null) {
                try {
                    ssc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            executor.shutdown();
        }
    }

    @Test
    public void testSocketSend() {
        final int START = 0;
        final int TIMES = 10;
        final int PERIOD = 1000;

        int i = START;
        while (i < PERIOD * TIMES + START) {
            String message = "hello world at " + System.currentTimeMillis();
            SocketChannel sc = null;
            try {
                sc = SocketChannel.open();
//            sc.configureBlocking(false);
                sc.connect(new InetSocketAddress(8000));

                ByteBuffer buffer = ByteBuffer.allocate(48);
                buffer.clear();
                buffer.put(message.getBytes("GBK"));
                buffer.flip();
                while (buffer.hasRemaining()) {
                    sc.write(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (sc != null) {
                    try {
                        sc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i += PERIOD;
        }
    }
}
