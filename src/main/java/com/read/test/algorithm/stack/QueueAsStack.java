package com.read.test.algorithm.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @see <a href="https://leetcode.cn/problems/implement-stack-using-queues/description/">用队列实现栈</a>
 */
public class QueueAsStack {

    private final Queue<Integer> queue = new LinkedList<>();

    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        // 旋转新元素前的所有元素
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
