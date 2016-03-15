package com.read.test.jedis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangread on 4/30/15.
 * <p/>
 * redis java client test
 */
public class JedisTest {

    private final static Log log = LogFactory.getLog(JedisTest.class);

    @Test
    public void test() {
        Jedis jedis = new Jedis("127.0.0.1", 6380);
//        jedis.set("hello", "world");
        System.out.println(jedis.get("name"));
    }

    @Test
    public void testJedisShard() {
        ShardedJedisPool writePool = null;
        ShardedJedisPool readPool = null;
        try {
            List<JedisShardInfo> wShards = new ArrayList<JedisShardInfo>();
            List<JedisShardInfo> rShards = new ArrayList<JedisShardInfo>();

            JedisShardInfo writeShard = new JedisShardInfo("127.0.0.1", 6379);
            JedisShardInfo readShard = new JedisShardInfo("127.0.0.1", 6380);

            wShards.add(writeShard);
            rShards.add(readShard);

            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(8);
            jedisPoolConfig.setMaxTotal(200);
            jedisPoolConfig.setTestOnBorrow(false);
            jedisPoolConfig.setMaxWaitMillis(10000);

            writePool = new ShardedJedisPool(jedisPoolConfig, wShards);
            readPool = new ShardedJedisPool(jedisPoolConfig, rShards);
        } catch (Exception e) {
            log.error(e);
        }

        if (writePool != null) {
            ShardedJedis jedis = writePool.getResource();
            jedis.set("test", "哈哈");
        }
        if (readPool != null) {
            ShardedJedis jedis = readPool.getResource();
            System.out.println(jedis.get("test"));
        }
    }

    @Test
    public void testJedisCluster() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7000));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7001));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7002));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7003));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7004));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7005));

        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
        jedisCluster.set("clustertest", "success");
        System.out.println(jedisCluster.get("clustertest"));

    }
}
