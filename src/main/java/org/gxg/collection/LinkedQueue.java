package org.gxg.collection;

import org.gxg.tools.In;

public class LinkedQueue<E> {
    private int N;

    private Node first;// 指向第1个添加的元素

    private Node last;// 指向最后1个添加的元素

    public LinkedQueue() {
        first = null;
        last = null;
    }

    private class Node {
        E element;
        Node next;
    }

    // 添加一个元素 (添加到尾部)
    // 旧的尾部元素的next 指向这个新元素
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

    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        In in = new In("testData/queue_test.txt");
        System.out.println("......queue enqueue test starter");
        while (!in.isEmpty()) {
            queue.enqueue(in.readInt());
            System.out.printf("queue size: %d\r\n", queue.size());
        }
        System.out.println("......queue enqueue test end");

        System.out.println("......queue dequeue test starter");
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int item = queue.dequeue();
            System.out.println("dequeue value: " + item);
            System.out.println("queue size: " + queue.size());
        }
        System.out.println("......queue dequeue test end");
        in.close();
    }
}
