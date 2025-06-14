package org.gxg.sort;

import org.gxg.tools.In;
import org.gxg.tools.StdRandom;

import java.util.Random;

public class Quick extends Common {
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
        assert isSorted(a);
        print_counter();
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
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
//        show(a);

        in.close();

    }
}
