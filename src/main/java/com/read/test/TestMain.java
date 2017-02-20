package com.read.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yfwangrui on 2014/12/10.
 */
public class TestMain {

    private final static Logger LOG = LoggerFactory.getLogger(TestMain.class);

    @Test
    public void test() {
//        Properties prop = new Properties();
//        prop.put("zookeeper.clientCnxnSocket", "ClientCnxnSocketNIO");
//        System.setProperties(prop);
//        System.out.println(System.getProperty("zookeeper.clientCnxnSocket"));

//        String dateStr = "2016-06-29 00:00:00";
//        String[] patterns = {"yyyy-MM-dd HH:mm:ss"};
//        try {
//            Date date = DateUtils.parseDate(dateStr, patterns);
//            System.out.println(date.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(1453910399000l);
//        System.out.println(calendar.getTime());
//        System.out.println(new Date().getTime());


//        String url = "http://ccc.x.jd.local/dsp/nc?ext=Y2xpY2sueC5qZC5jb20vSmRDbGljay8_eHVpZD01MjAwNyZ4c2l0ZWlkPTIwNzk0NF8xNjc3JnRvPWh0dHA6Ly9pdGVtLm0uamQuY29tL3Byb2R1Y3QvMTE1MTQwNDkuaHRtbA&log=t9ODlIhvPUfboiA9fxvMrbFqLiYEzdCoAvD3whuraW0xMbOGNWEAfx04F95jNdISoxoMtiKQCMyj4hHYM7sZDZa2O9hS3pSOoERMq6UaU6LE9ZQQFmBoCIf963QSXvQVsvWAm9ugU4R4EJgJ3JrrMS2eJYAUlI8hi3HLqPMHh9BHpNeAjoRb3OpxpP60Fme9jNFZ6c32yjNG_d7kMW08-7ScjKUtVoSaxhjsjMo3VB7B1BEfR9FD_XUnCbDEizKF6Vj1IEL6Kvfxom9ILFy9Yw0nYdD3Z7OKKzAijagcj4BKKlrlc6ODvdUCJa8LMN_0ywrZMogfsKzBGflgYoHgrpP1C36cQDPleQ93XJg0pOIVrzo1q5rEjNdR1Ukgq5hFVKOT4oDyx_BqCnStuu6rYQ&v=404&ru=http%3a%2f%2fmercury.jd.local%2flog.gif%3ft%3drec.000000%26v%3dsrc%3dmix$action%3d8$sku%3d11614401$csku%3d11514049$adposid%3d1677$adsid%3dc22faa20-8fa9-43bb-a17d-e511cb5eb7c7&clicktype=1&";
//        try {
//            System.out.println(URLEncoder.encode(url, "iso-8859-1"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
//        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
//        ThreadInfo mainThread = null;
//
//        for (ThreadInfo threadInfo : threadInfos) {
//            if ("main".equals(threadInfo.getThreadName())) {
//                mainThread = threadInfo;
//            }
//        }
//
//        if (mainThread != null) {
//            StackTraceElement[] elements = mainThread.getStackTrace();
//            for (StackTraceElement element : elements) {
//                System.out.println(element.toString());
//            }
//        }
        LOG.error("123");
    }
}
