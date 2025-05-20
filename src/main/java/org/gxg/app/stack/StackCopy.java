package org.gxg.app.stack;

import org.gxg.collection.ArrayStack;
import org.gxg.tools.In;

import java.util.Arrays;

public class StackCopy {
    public static ArrayStack<String> copy(ArrayStack<String> stack) {
        ArrayStack<String> newstack = new ArrayStack<>();

        // TODO

        return newstack;
    }

    public static void main(String[] args) {
        ArrayStack<String> stk = new ArrayStack<>();
        In in = new In("testData/sort_words.txt");
        while (!in.isEmpty()) {
            String item = in.readString();
            stk.push(item);
        }

        ArrayStack<String> newstack = copy(stk);

        System.out.println(" ");
        System.out.println("..............stack size: " + stk.size());
        System.out.println("stack pop: " + stk.pop());
        System.out.println("stack size: " + stk.size());

        System.out.println("..............new stack size: " + newstack.size());
        System.out.println("new stack pop1: " + newstack.pop());
        System.out.println("new stack pop2: " + newstack.pop());
        System.out.println("new stack size: " + newstack.size());

    }
}
