package org.gxg.sort;

import org.gxg.tools.In;

public class Merge extends Common {
    static Comparable[] aux;
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {// 只剩1个元素
            return;
        }
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);// 左半边排序
        sort(a, mid + 1, hi);// 右半边排序
        merge(a, lo, mid, hi);// 合并结果
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        // 将待排序数据临时存入 aux 对应的位置
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        int k = lo;
        int j = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (k > mid) a[i] = aux[j++];
            else if (j > hi) a[i] = aux[k++];
            else if (less(aux[k], aux[j])) {
                a[i] = aux[k++];
            } else {
                a[i] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
//        String file = "testData/sort_words.txt";
        String file = "testData/sort_tiny.txt";
        In in = new In(file);
        String[] a = in.readAllStrings();
        sort(a);
        show(a);

        in.close();
    }
}
