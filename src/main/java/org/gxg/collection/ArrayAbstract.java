package org.gxg.collection;

import java.util.Iterator;

abstract public class ArrayAbstract<Item> implements Iterable<Item> {
    protected Item[] a;
    protected int N;

    @SuppressWarnings("unchecked")
    public ArrayAbstract() {
        a = (Item[]) new Object[1];
        N = 0;
    }

    /**
     * 扩大栈容量
     * @param maxSize 扩大后的容量
     */
    @SuppressWarnings("unchecked")
    protected void resize(int maxSize) {
        Item[] tmp = (Item[]) new Object[maxSize];
        for (int i = 0; i < N; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {}
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);// 扩容2倍
        }
        a[N++] = item;
    }

    // 非标准方法，仅为展示扩容过程
    public int capacity() {
        return a.length;
    }

    public Item pop() {
        if (isEmpty()) {
            return null;
        }
        Item item = a[--N];
        a[N] = null;

        if (N == a.length/4) {// 如果占用不足1/4，则缩容1倍
            resize(a.length/2);
        }

        return item;
    }
}