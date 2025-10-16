package com.read.test.algorithm.sort;

import java.util.*;

public class HeapSort {

    /**
     * @see <a href="https://leetcode.cn/problems/top-k-frequent-words/description/">前K个高频单词</a>
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(k, (entry1, entry2) -> entry1.getValue().equals(entry2.getValue()) ? entry2.getKey().compareTo(entry1.getKey()) : entry1.getValue() - entry2.getValue());
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            res.add(queue.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * @see <a href="https://leetcode.cn/problems/kth-largest-element-in-an-array/">数组中的第K个最大元素</a>
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }
}
