package org.gxg.collection;

import org.gxg.tools.In;
import org.gxg.util.ArrayList;

public class ArrayListQueue<E> {
    ArrayList<E> list;
    public ArrayListQueue() {
        list = new ArrayList<>();
    }
    // 添加一个元素 (添加到尾部)
    // 添加到 back 所指位置，添加后back向后移动一位
    public void enqueue(E element) {
        list.add(element);
    }

    // 删除最早添加的元素
    //
    public E dequeue() {
        return list.remove(0);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        ArrayListQueue<Integer> queue = new ArrayListQueue<>();
        In in = new In("testData/queue_test.txt");
        while (!in.isEmpty()) {
            queue.enqueue(in.readInt());
            System.out.printf("queue size: %d\r\n", queue.size());
        }
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int item = queue.dequeue();
            System.out.println("dequeue value: " + item);
            System.out.printf("queue size: %d\r\n", queue.size());
        }
        in.close();
    }
}
