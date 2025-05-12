package org.gxg.collection;

import org.gxg.tools.In;

import java.util.Iterator;

public class LinkedStackBySentinel<E> implements Iterable<E> {
    private int N;// 元素数量
    /**
     * 把他当成哨兵节点（Sentinel Node)，特点在于：
     * 1、作为虚拟节点，不存储实际值(element字段不存值)
     * 2、next字段指向最新添加的节点
     *
     */
    Node first;

    private class Node {
        Node next;
        E element;
    }
    public LinkedStackBySentinel() {
        N = 0;
        first = new Node();
        first.element = null;
        first.next = null;
    }

    public void push(E element) {
        Node newNode = new Node();
        newNode.element = element;
        newNode.next = first.next;
        first.next = newNode;
//
//        Node oldFirst = first;
//        first = new Node();
//        first.element = element;
//        first.next = oldFirst;
        N++;
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E element = first.next.element;
        first.next = first.next.next;
        N--;
        return element;
//
//        E element = first.element;
//        first = first.next;
//        N--;
//        return element;
    }

    public boolean isEmpty() {
        return N == 0;
//        return first.next == null;
    }

    public int size() {
        return N;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<E> {
        Node currNode = first.next;

        @Override
        public boolean hasNext() {
            return currNode != null;
        }

        @Override
        public E next() {
            E element = currNode.element;
            currNode = currNode.next;
            return element;
        }
    }

    public static void main(String[] args) {
        LinkedStackBySentinel<String> stk = new LinkedStackBySentinel<>();
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
        System.out.println("......Iterator test starter");
        for (String s : stk) {
            System.out.println(s);
        }
        System.out.println("......Iterator test end");


        LinkedStackBySentinel<Character> stkchr = new LinkedStackBySentinel<>();
        in = new In("testData/stack_test.txt");
        System.out.println("push test stater...");
        while (!in.isEmpty()) {
            char item = in.readChar();
            stkchr.push(item);
        }
        System.out.println("stack size: " + stkchr.size());
        System.out.println("push test end....");
        in.close();
        System.out.println();
        System.out.println("......Iterator test starter");
        for (Character c : stkchr) {
            System.out.println("char is: " + c);
        }
        System.out.println("......Iterator test end");


        System.out.println("pop test stater...");
        while (!stkchr.isEmpty()) {
            char c = stkchr.pop();
            System.out.print(c);
        }
        System.out.println();
        System.out.println("pop test end....");
    }
}
