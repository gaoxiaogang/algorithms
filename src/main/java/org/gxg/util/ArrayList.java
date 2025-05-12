package org.gxg.util;

public class ArrayList<E> {
    private E[] Data;
    private int N;// 元素数量

    @SuppressWarnings("unchecked")
    public ArrayList() {
        Data = (E[]) new Object[8];
        N = 0;
    }

    /**
     * 扩大栈容量
     * @param maxSize 扩大后的容量
     */
    @SuppressWarnings("unchecked")
    private void resize(int maxSize) {
        E[] tmp = (E[]) new Object[maxSize];
        for (int i = 0; i < N; i++) {
            tmp[i] = Data[i];
        }
        Data = tmp;
    }

    // 在指定位置插入
    public void add(int index, E element) {
        if (index > N) {
            throw new IndexOutOfBoundsException("index > N");
        }
        if (N == Data.length) {
            resize(Data.length * 2);
        }

        // 允许index等于N，意味着在最后插入
        // 先搬家腾位置
        // 如果index等于N，for循环不满足条件，也就不用挪家腾位置
        for (int i = N; i > index; i--) {// 从index开始的元素全部向后移一个位置
            Data[i] = Data[i-1];
        }
        // 再把元素放入腾出来的空位置
        Data[index] = element;
        N++;
    }

    // 在最后插入
    public void add(E element) {
        if (N == Data.length) {
            resize(Data.length * 2);
        }
        Data[N++] = element;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    // 获取指定位置的元素
    public E get(int index) {
        if (index >= N) {
            return null;
        }
        return Data[index];
    }

    // 删除指定位置的元素
    public E remove(int index) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        // 见对应的测试用例
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("num4 is: " + list.get(3));
        list.add(1);
        System.out.println("num1 is: " + list.get(0));
        list.add(2);
        System.out.println("num2 is: " + list.get(1));
        list.add(0, 3);
        list.add(list.size(), 4);
        list.add(2, 5);
    }
}
