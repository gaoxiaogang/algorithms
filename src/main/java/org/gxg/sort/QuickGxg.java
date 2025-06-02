package org.gxg.sort;

import org.gxg.tools.Counter;
import org.gxg.tools.In;
import org.gxg.tools.Out;

import java.util.Random;

public class QuickGxg extends Common {
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
    @SuppressWarnings("rawtypes")
    public static void sort(Comparable[] a) {
        init_counter();

//        StdRandom.shuffle(a);
        shuffle(a);
        sort(a, 0, a.length - 1);

        print_counter();
    }

    // quicksort the subarray from a[lo] to a[hi]
    @SuppressWarnings({"rawtypes"})
    private static void sort(Comparable[] a, int lo, int hi) {
//        System.out.println();
//        System.out.println();
//        System.out.printf("【递归sort】 lo is: %d, hi is: %d",lo,hi);
//        System.out.println();
//        show(a, lo, hi);

        if (hi <= lo) {
            return;
        }

        // Bentley-McIlroy 3-way partitioning
        int i = lo, j = hi+1;
        int p = lo, q = hi+1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == lo) break;

            // pointers cross
            if (i == j && eq(a[i], v)) {
                exch(a, ++p, i);
            }
            if (i >= j) break;

            exch(a, i, j);

            if (eq(a[i], v)) {
                exch(a, ++p, i);
            }
            if (eq(a[j], v)) {
                exch(a, --q, j);
            }
        }
//        System.out.print("while done::");
//        System.out.printf("i is: %d, j is: %d", i, j);
//        System.out.println();
//        show(a, lo, hi);

        i = j + 1;
        int lefts = j - p;
        int rights = q - i;
        for (int k = lo; k <= p && j > p; k++) {
//            System.out.printf("exch k is: %d, j is: %d",k,j);
//            System.out.println();
            exch(a, k, j--);
        }

        for (int k = hi; k >= q && q > i; k--) {
//            System.out.printf("exch k is: %d, i is: %d",k,i);
//            System.out.println();
            exch(a, k, i++);
        }

        sort(a, lo, lo+lefts-1);
        sort(a, hi-rights+1, hi);
    }

    public static void main(String[] args) {
//        Out out = new Out("testData/sort_large_same_words.txt");
//        for (int i = 0; i < 30000; i++) {
//            out.println("Hello");
//        }
//        out.close();
//        return;

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
