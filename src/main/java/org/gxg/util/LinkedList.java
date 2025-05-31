package org.gxg.util;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {
    private int N;// 元素数量
    private final Node<E> front;
    private final Node<E> back;

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    private class InnerIterator implements Iterator<E> {
        private int i = N;
        private Node<E> curr = front;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public E next() {
            i--;
            E item = curr.next.item;
            curr = curr.next;
            return item;
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E element) {
            item = element;
            next = null;
            prev = null;
        }

        public Node(E element, Node<E> prev, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedList() {
        N = 0;
        front = new Node<>(null);
        back = new Node<>(null);
        front.next = back;
        back.prev = front;
    }

    // 在指定位置插入
    public void add(int index, E element) {
        if (index > N) {
            throw new IndexOutOfBoundsException();
        }
        N++;
        Node<E> curr = front.next;
        while (index > 0) {
            curr = curr.next;
            index--;
        }

        Node<E> newNode = new Node<>(element, curr.prev, curr);
        curr.prev.next = newNode;
        curr.prev = newNode;
    }

    // 在最后插入
    public void add(E element) {
        N++;
        Node<E> newNode = new Node<>(element, back.prev, back);
        back.prev.next = newNode;
        back.prev = newNode;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    // 获取指定位置的元素
    public E get(int index) {
        if (index >= N) {
            return null;
        }
        Node<E> curr = front.next;
        while (index > 0) {
            curr = curr.next;
            index--;
        }
        return curr.item;
    }

    // 删除指定位置的元素
    public E remove(int index) {
        if (index >= N) {
            return null;
        }

        N--;

        Node<E> curr = front.next;
        while (index > 0) {
            curr = curr.next;
            index--;
        }

        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        return curr.item;
    }

    public static void main(String[] args) {
        // 见对应的测试用例
        LinkedList<Integer> list = new LinkedList<>();
        System.out.println("num4 is: " + list.get(3));
        list.add(1);
        System.out.println("num1 is: " + list.get(0));
        list.add(2);
        System.out.println("num2 is: " + list.get(1));
        list.add(0, 3);
        list.add(list.size(), 4);
        list.add(2, 5);
        for (Integer i : list) {
            System.out.println(i);
        }

        System.out.println("remove idx 0: " + list.remove(0));
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
