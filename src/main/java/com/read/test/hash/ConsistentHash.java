package com.read.test.hash;

import redis.clients.util.Hashing;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by yfwangrui on 2015/6/2.
 * <p/>
 * consistent hashing
 */
public class ConsistentHash<T> {

    private final Hashing hashing;
    private final int numberOfReplicas;
    private final SortedMap<Long, T> circle = new TreeMap<Long, T>();

    public ConsistentHash() {
        this(Hashing.MD5, Integer.MAX_VALUE, null);
    }

    public ConsistentHash(Hashing hashing) {
        this(hashing, Integer.MAX_VALUE, null);
    }

    public ConsistentHash(Hashing hashing, int numberOfReplicas) {
        this(hashing, numberOfReplicas, null);
    }

    public ConsistentHash(Hashing hashing, int numberOfReplicas,
                          Collection<T> nodes) {
        this.hashing = hashing;
        this.numberOfReplicas = numberOfReplicas;
        if (nodes != null) {
            for (T node : nodes) {
                add(node);
            }
        }
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashing.hash(node.toString() + i), node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashing.hash(node.toString() + i));
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        long hash = hashing.hash(String.valueOf(key));
        if (!circle.containsKey(hash)) {
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

}