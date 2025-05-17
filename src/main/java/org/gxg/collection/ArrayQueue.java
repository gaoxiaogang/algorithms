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
        front = Data.length - 1;
        back = 0;
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
        if (N == Data.length) {
            resize(Data.length * 2);
        }
        Data[back] = element;
        back = increment(back);
        N++;
    }

    // 删除最早添加的元素
    //
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        front = increment(front);
        E e = Data[front];
        Data[front] = null;
        N--;
        // 考虑缩小容量
        if (Data.length > 8 && N == Data.length/4) {// 如果占用不足1/4，则缩容1倍
            resize(Data.length/2);
        }
        return e;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
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
