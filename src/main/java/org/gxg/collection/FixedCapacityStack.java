package org.gxg.collection;

/**
 * 基于数组实现的固定容量通用栈
 */
abstract public class FixedCapacityStack<Item> {
    private Item[] a;// stack entries

    private int N;// size

    public FixedCapacityStack(int cap){
        // TODO
    };

    /**
     * 添加一个字符串
     */
    abstract public void push(Item item);

    /**
     * 删除最近添加的字符串
     */
    abstract public Item pop();

    /**
     * 栈是否为空
     */
    abstract public boolean isEmpty();

    /**
     * 栈中元素数量
     */
    abstract public int size();
}
