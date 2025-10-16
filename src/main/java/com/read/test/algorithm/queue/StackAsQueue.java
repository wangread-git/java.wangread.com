package com.read.test.algorithm.queue;

import java.util.Stack;

/**
 * @see <a href="https://leetcode.cn/problems/implement-queue-using-stacks/">用栈实现队列</a>
 */
public class StackAsQueue {

    private final Stack<Integer> pushStack;
    private final Stack<Integer> popStack;

    public StackAsQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void push(int x) {
        pushStack.push(x);
    }

    public int pop() {
        peek();
        return popStack.pop();
    }

    public int peek() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.peek();
    }

    public boolean empty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }
}
