package org.gxg.collection;

import org.gxg.tools.In;
import org.gxg.tools.StdIn;

/**
 * 基于数组实现的固定容量通用栈
 */
public class FixedCapacityStack<Item> {
    private final Item[] a;// stack entries

    private int N;// size

    @SuppressWarnings("unchecked")
    public FixedCapacityStack(int cap){
        a = (Item[]) new Object[cap];
        N = 0;
    }

    /**
     * 添加一个字符串
     */
    public void push(Item item) {
        if (N == a.length) {
            throw new ArrayIndexOutOfBoundsException("Array a is full!");
        }
        a[N++] = item;
    }

    /**
     * 删除最近添加的字符串
     */
    public Item pop() {
        if (isEmpty()) {
            return null;
        }
        Item item = a[--N];
        a[N] = null;// TODO 这句代码是否可以去掉？
        return item;
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
        FixedCapacityStack<String> stk = new FixedCapacityStack<>(cap);

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
