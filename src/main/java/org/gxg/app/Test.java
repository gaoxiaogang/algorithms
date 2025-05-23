package org.gxg.app;

import org.gxg.collection.ArrayStack;
import org.gxg.tools.In;
import org.gxg.tools.Stopwatch;

public class Test {
    public static void main(String[] args) {

        // 实验结论：循环非常非常快，可以忽略这个成本
        int n = 100000;
        Integer[] arr = new Integer[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = arr[i];
                arr[i] = arr[n-1];
                arr[n-1] = tmp;
            }
        }
        System.out.printf("foreach %d x %d times: %f", n, n, timer.elapsedTime());
        System.out.println();

        ArrayStack<String> stk = new ArrayStack<>();
        In in = new In("testData/sort_words.txt");
        while (!in.isEmpty()) {
            String item = in.readString();
            stk.push(item);
        }

        ArrayStack<String> newstack = stk;

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
