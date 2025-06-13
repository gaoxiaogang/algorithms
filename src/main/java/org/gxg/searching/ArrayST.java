package org.gxg.searching;

// 基于无序数组的符号表实现
@SuppressWarnings("unchecked")
public class ArrayST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 8;
    private Key[] keys;// 存放key
    private Value[] vals;// 存放值
    private int N = 0;// 元素数量

    public ArrayST() {
        this(INIT_CAPACITY);
    }

    public ArrayST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        // TODO
        return false;
    }

    public Value get(Key key) {
        // TODO
        return null;
    }

    public void put(Key key, Value val) {
        // TODO
    }

    public void delete(Key key) {
        // TODO
    }

    public Iterable<Key> keys() {
        // TODO
        return null;
    }

    public int size() {
        // TODO
        return 0;
    }

    public boolean isEmpty() {
        // TODO
        return true;
    }

}
