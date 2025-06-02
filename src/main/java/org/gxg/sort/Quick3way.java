package org.gxg.sort;

import org.gxg.tools.In;

import java.util.Random;

@SuppressWarnings("rawtypes")
public class Quick3way extends Common {
    // 打扰 a 的顺序
    private static void shuffle(Object[] a) {
        Random random = new Random(System.currentTimeMillis());
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + random.nextInt(n-i);     // between i and n-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    public static void sort(Comparable[] a) {
        init_counter();

//        StdRandom.shuffle(a);
        shuffle(a);
        sort(a, 0, a.length - 1);

        print_counter();
    }

    // 比较。单独放出来，目的在于调用计数统计
    @SuppressWarnings({"unchecked"})
    private static int compareTo(Comparable v, Comparable w) {
        compare_counter.increment();
        return v.compareTo(w);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) {
//        System.out.println();
//        System.out.println();
//        System.out.printf("【递归sort】 lo is: %d, hi is: %d",lo,hi);
//        System.out.println();
//        show(a, lo, hi);

        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = compareTo(a[i], v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }
//        System.out.println("while done:");
//        System.out.printf("lt is: %d, gt is: %d", lt, gt);
//        System.out.println();
//        show(a, lo, hi);

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    public static void main(String[] args) {
//        String file = "testData/sort_words.txt";
//        String file = "testData/sort_tiny.txt";
//        String file = "testData/sort_same_words.txt";
        String file = "testData/sort_large_same_words.txt";
//        String file = "testData/sort_words.shakespeare.txt";
        In in = new In(file);
        String[] a = in.readAllStrings();
        sort(a);
//        System.out.println();
//        System.out.println("sorted:");
//        show(a);

        in.close();
    }
}
