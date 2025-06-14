package org.gxg.collection;

import org.gxg.tools.In;

import java.util.Iterator;

public class LinkedQueue<E> implements Iterable<E> {
    private int N;

    private Node first;// 指向第1个添加的元素

    private Node last;// 指向最后1个添加的元素

    public LinkedQueue() {
        first = null;
        last = null;
    }

    @Override
    public Iterator iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
        private Node currentNode = first;
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            E element = currentNode.element;
            currentNode = currentNode.next;
            return element;
        }
    }

    private class Node {
        E element;
        Node next;
    }

    // 添加一个元素 (添加到尾部)
    // 旧的尾部元素的next 指向这个新元素
    public void enqueue(E element) {
        Node oldBack = last;
        last = new Node();
        last.element = element;
        if (isEmpty()) {
            first = last;
        } else {
            oldBack.next = last;
        }
        N++;
    }

    // 删除最早添加的元素
    //
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        if (N == 1) {
            last = null;
        }
        Node firstNode = first;
        first = firstNode.next;
        N--;
        return firstNode.element;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
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

        System.out.print("[");
        for (Integer i : queue) {
            System.out.printf(i + ", ");
        }
        System.out.print("]");
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
