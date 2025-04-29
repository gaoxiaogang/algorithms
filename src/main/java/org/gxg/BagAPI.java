package org.gxg;

public interface BagAPI<E> {
    /**
     * 添加一个元素
     */
    void add(E e);

    /**
     * 背包是否为空
     */
    boolean isEmpty();

    /**
     * 背包中有多少元素
     */
    int size();
}

