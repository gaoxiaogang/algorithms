package org.gxg.collection;

import org.gxg.tools.In;

// 先进先出队列 FIFO (First In, First Out)
public class ArrayQueue<E> {
    E[] Data;// 用于存放元素的数组
    int N;// 元素数量
    int front;// 指向队列头的前一个空位置
    int back;// 指向队列尾的后一个空位置

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        Data = (E[]) new Object[8];
        N = 0;
        front = 0;
        back = 1;
    }

    // 队列扩容
    @SuppressWarnings("unchecked")
    private void resize(int maxSize) {
        E[] tmp = (E[]) new Object[maxSize];

        int k = increment(front);
        for (int i = 0; i < N; i++) {
            tmp[i] = Data[k];
            k = increment(k);
        }

        Data = tmp;
        front = Data.length - 1;
        back = N;
    }

    private int increment(int idx) {
        if (idx == Data.length - 1) {
            return 0;
        }
        return idx+1;
    }

    // 添加一个元素 (添加到尾部)
    // 添加到 back 所指位置，添加后back向后移动一位
    public void enqueue(E element) {
        // TODO
    }

    // 删除最早添加的元素
    //
    public E dequeue() {
        // TODO
        return null;
    }

    public int size() {
        // TODO
        return 0;
    }

    public boolean isEmpty() {
        // TODO
        return true;
    }

    // 非标准方法，仅为展示扩容过程
    public int capacity() {
        return Data.length;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        In in = new In("testData/queue_test.txt");
        while (!in.isEmpty()) {
            queue.enqueue(in.readInt());
            System.out.printf("queue capacity: %d, size: %d\r\n", queue.capacity(), queue.size());
        }
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int item = queue.dequeue();
            System.out.println("dequeue value: " + item);
            System.out.printf("queue capacity: %d, size: %d\r\n", queue.capacity(), queue.size());
        }
        in.close();
    }
}
