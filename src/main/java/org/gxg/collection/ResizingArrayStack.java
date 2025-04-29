package org.gxg.collection;

abstract public class ResizingArrayStack<Item> {
    private Item[] a;
    private int N;

    /**
     *
     */
    @SuppressWarnings("unchecked")
    public ResizingArrayStack() {
        a = (Item[]) new Object[1];
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
            tmp[i] = a[i];
        }
        a = tmp;
    }

    abstract public boolean isEmpty();

    abstract public int size();

    abstract public void push(Item item);

    abstract public Item pop();


}
