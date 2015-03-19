package com.read.test.jdk.net;

import org.junit.Test;

import java.io.*;
import java.net.*;

/**
 * Created by yfwangrui on 2015/3/12.
 */
public class SocketTest {

    @Test
    public void serverTest() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(9000);

            Socket socket = null;
            try {
                socket = server.accept();
                InputStream in = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                System.out.println(sb);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void clientTest() {
        Socket socket = null;
        OutputStream out = null;
        try {
            socket = new Socket("127.0.0.1", 9000);
            out = socket.getOutputStream();
            long i = 0;
            while (i < 60000) {
                String msg = "hello world at " + System.currentTimeMillis() + "\n";
                out.write(msg.getBytes());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i += 10000;
                System.out.println(i);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void udpSendTest() {
        DatagramSocket socket = null;
        InetAddress address;
        try {
            socket = new DatagramSocket();
            String str = "hello world";
            byte[] buffer = str.getBytes();
            address = InetAddress.getLocalHost();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 80);
            socket.send(packet);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    @Test
    public void udpReceiveTest() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(80);

            byte[] buffer = new byte[10];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);
            System.out.println(new String(packet.getData()));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    @Test
    public void testUrlConnection() {
        URL url;
        BufferedReader reader = null;
        try {
            url = new URL("http://promotionsoa.m.jd.com/homePage?pin=wangread_jd&body=%7B%22longitude%22%3A%2239.793230%22%2C%22coordinateType%22%3A%22baidu%22%2C%22latitude%22%3A%22116.506010%22%7D&uuid=863151027445581-38bc1a9f3f89&clientVersion=4.0.1&client=android&d_brand=Meizu&d_model=M355&osVersion=4.2.1&screen=1800*1080&partner=jingdong&area=1_2806_4187_0&networkType=wifi&sign=F3fl8Drz-KRvB3NiqZPP0w&sv=1&st=1415069972977");
            URLConnection connection = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println(sb);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
