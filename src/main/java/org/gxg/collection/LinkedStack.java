package org.gxg.collection;

import org.gxg.tools.In;

import java.util.Iterator;

public class LinkedStack<E> implements Iterable<E> {
    private int N;// 元素数量
    Node first;// 指向最新添加的节点

    private class Node {
        Node next;
        E element;
        public Node(E e, Node n) {
            element = e;
            next = n;
        }
    }
    public LinkedStack() {
        N = 0;
        first = null;
    }

    public void push(E element) {
        // TODO
    }

    public E pop() {
        // TODO
        return null;
    }

    public boolean isEmpty() {
        return N == 0;
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
        Node currNode = first;

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
        LinkedStack<String> stk = new LinkedStack<>();
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


        LinkedStack<Character> stkchr = new LinkedStack<>();
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
