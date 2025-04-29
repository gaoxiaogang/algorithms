package org.gxg.collection;

public class ArrayQueue<E> {
    E[] a;
    int N;
    int first;// 指向队列头

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        a = (E[]) new Object[1];
        N = 0;
        first = 0;
    }

    // 队列扩容
    private void resize(int maxSize) {
        // TODO
    }

    public void enqueue(E element) {
        // TODO
    }

    // 从队列头取一个元素
    public E dequeue() {
        // TODO
        E e = a[first];
        return e;

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
        System.out.println("Hello");
    }
}
