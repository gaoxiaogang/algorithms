package org.gxg.collection;

import org.gxg.tools.In;
import org.gxg.tools.StdIn;

/**
 * 基于数组实现的固定容量字符串栈
 */
public class FixedCapacityStackOfStrings extends ArrayAbstract {
    private String[] a;// stack entries

    private int N;// size

    /**
     *
     * @param cap 指定栈的大小
     */
    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
        N = 0;
    }

    /**
     * 添加一个字符串
     * TODO
     * @param item 入栈的元素
     */
    public void push(String item) {
        a[N++] = item;
    }

    /**
     * 删除最近添加的字符串
     * TODO
     * @return String
     */
    public String pop() {
        String s = a[--N];
        a[N] = null;
        return s;
    }

    /**
     * 栈是否为空
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 栈中元素数量
     */
    public int size() {
        return N;
    }

    public static void main(String[] args) {
        System.out.print("请设置Stack容量：");
        int cap = StdIn.readInt();
        FixedCapacityStackOfStrings stk = new FixedCapacityStackOfStrings(cap);
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
    }

}
