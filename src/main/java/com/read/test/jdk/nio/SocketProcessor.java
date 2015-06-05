package com.read.test.jdk.nio;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yfwangrui on 2015/6/5.
 * <p/>
 * process socket
 */
public class SocketProcessor implements Runnable {

    private SocketChannel sc;

    public SocketProcessor(SocketChannel sc) {
        this.sc = sc;
    }

    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            sc.configureBlocking(false);
            int result = sc.read(buffer);
            List<Byte> byteList = new ArrayList<Byte>();
            while (sc.isConnected() && result != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byteList.add(buffer.get());
                }
                byte[] bytes = new byte[byteList.size()];
                for (int i = 0; i < byteList.size(); i++) {
                    bytes[i] = byteList.get(i);
                }
                String message = new String(bytes, "GBK");
                if (StringUtils.isNotEmpty(message)) {
                    System.out.print(message);
                }
                buffer.clear();
                byteList.clear();
                result = sc.read(buffer);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
