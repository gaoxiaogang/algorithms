package org.gxg.sort;

import org.gxg.tools.Counter;

@SuppressWarnings({"rawtypes", "unchecked"})
abstract public class Common {
    // 执行时间计数器
    protected static Counter exch_counter;
    protected static Counter compare_counter;

    // 初始化计数器
    protected static void init_counter() {
        exch_counter = new Counter("exch");
        compare_counter = new Counter("compare");
    }

    protected static void print_counter() {
        System.out.println("stats:");
        System.out.println(exch_counter.toString());
        System.out.println(compare_counter.toString());
    }

    // This class should not be instantiated.
    protected Common() { }

    // is v < w ?
    @SuppressWarnings("unchecked")
    protected static boolean less(Comparable v, Comparable w) {
        compare_counter.increment();
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    protected static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
        exch_counter.increment();
    }

    // does v == w ?
    protected static boolean eq(Comparable v, Comparable w) {
        compare_counter.increment();
        if (v == w) return true;    // optimization when reference equal
        return v.compareTo(w) == 0;
    }

    // is the array a[] sorted?
    protected static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    protected static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    protected static void show(Comparable[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println("]");
    }

    // print array to standard output
    protected static void show(Comparable[] a, int lo, int hi) {
        System.out.print("[");
        for (int i = lo;i <= hi; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println("]");
    }
}
