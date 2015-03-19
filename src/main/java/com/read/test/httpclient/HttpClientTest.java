package com.read.test.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.junit.Test;

/**
 * Created by yfwangrui on 2014/12/15.
 */
public class HttpClientTest {

    @Test
    public void test() {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(connectionManager);
    }
}
