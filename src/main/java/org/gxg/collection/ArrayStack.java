package org.gxg.collection;

import org.gxg.tools.In;
import java.util.Iterator;

// 后进先出 LIFO (Last In, First Out)
public class ArrayStack<Item> implements Iterable<Item> {
    private Item[] Data;
    private int N;// 元素数量

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        Data = (Item[]) new Object[1];
        N = 0;
    }

    /**
     * 扩大栈容量
     * @param maxSize 扩大后的容量
     */
    @SuppressWarnings("unchecked")
    private void resize(int maxSize) {
        Item[] tmp = (Item[]) new Object[maxSize];
        for (int i = 0; i < N; i++) {
            tmp[i] = Data[i];
        }
        Data = tmp;
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
            return Data[--i];
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
        if (N == Data.length) {
            resize(2 * Data.length);// 扩容2倍
        }
        Data[N++] = item;
    }

    // 非标准方法，仅为展示扩容过程
    public int capacity() {
        return Data.length;
    }

    public Item pop() {
        if (isEmpty()) {
            return null;
        }
        Item item = Data[--N];
        Data[N] = null;

        if (Data.length >= 4 && N == Data.length/4) {// 如果占用不足1/4，则缩容1倍
            resize(Data.length/2);
        }

        return item;
    }

    public static void main(String[] args) {
        ArrayStack<String> stk = new ArrayStack<>();
        In in = new In("testData/stack_test.txt");
        while (!in.isEmpty()) {
            String item = in.readString();
            if (item.equals("-")) {
                System.out.print(stk.pop() + " ");
            } else {
                stk.push(item);
            }
        }
        System.out.println(" ");
        System.out.println("stack size: " + stk.size());
        System.out.println("Iterator test:");
        for (String s : stk) {
            System.out.println(s);
        }


        ArrayStack<Character> stkchr = new ArrayStack<>();
        in = new In("testData/stack_test.txt");
        while (!in.isEmpty()) {
            char item = in.readChar();
            stkchr.push(item);
            System.out.printf("stack capacity: %d, size: %d\r\n", stkchr.capacity(), stkchr.size());
        }
        in.close();
        System.out.println(" ");
        System.out.println("Iterator test:");
        for (Character c : stkchr) {
            System.out.println("char is: " + c);
        }
        while (!stkchr.isEmpty()) {
            char c = stkchr.pop();
            System.out.println("pop value: " + c);
            System.out.printf("stack capacity: %d, size: %d\r\n", stkchr.capacity(), stkchr.size());
        }
    }

}
