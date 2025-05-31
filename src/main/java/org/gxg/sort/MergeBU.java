package org.gxg.sort;

import org.gxg.tools.In;

public class MergeBU extends Common {
    private static Comparable[] aux;// 合并所需的辅助数组

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];

        for (int sz = 1; sz < N; sz += sz) {// sz表示子数组大小(size)。i == 1, 2, 4, 8, ...
            for (int j = 0; j < N-sz; j += sz+sz) {// j表示子数组索引
                merge(a, j, j+sz-1, Math.min(j+sz+sz-1, N-1));
            }
        }
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
        String file = "testData/sort_words.txt";
//        String file = "testData/sort_tiny.txt";
        In in = new In(file);
        String[] a = in.readAllStrings();
        sort(a);
        show(a);

        in.close();
    }
}
