package org.gxg.collection;

import org.gxg.tools.In;

public class ArrayStack<Item> extends ArrayAbstract<Item> {
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
