package com.read.test.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Test;

import java.util.List;

/**
 * Created by yfwangrui on 2015/7/10.
 * <p/>
 * path cache examples
 */
public class CuratorExample {

    private final static String connectionStr = "localhost:2181,localhost:2182,localhost:2183";
    private final static String curatorRoot = "/curator";

    @Test
    public void testCuratorFramework() {
        CuratorFramework client = null;
        PathChildrenCache cache = null;
        try {
            client = CuratorFrameworkFactory.newClient(connectionStr, new ExponentialBackoffRetry(1000, 3));
            client.start();

            cache = new PathChildrenCache(client, curatorRoot, true);
            cache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

            PathChildrenCacheListener listener = new PathChildrenCacheListener() {
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    System.out.println(event.getType());
                }
            };
            cache.getListenable().addListener(listener);

            if (client.checkExists().forPath(curatorRoot) == null) {
//                clear(client, curatorRoot);
                create(client, curatorRoot);
            }

            List<ChildData> childList = cache.getCurrentData();
            for (ChildData child : childList) {
                System.out.println(new String(child.getData()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(cache);
            CloseableUtils.closeQuietly(client);
        }
    }

    private void clear(CuratorFramework client, String path) {
        try {
            List<String> childList = client.getChildren().forPath(path);
            for (String child : childList) {
                clear(client, path + "/" + child);
            }
            client.delete().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void create(CuratorFramework client, String path) {
        try {
            client.create().forPath(curatorRoot, "root".getBytes());
            client.create().forPath(curatorRoot + "/child1", "child1".getBytes());
            client.create().forPath(curatorRoot + "/child2", "child2".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
