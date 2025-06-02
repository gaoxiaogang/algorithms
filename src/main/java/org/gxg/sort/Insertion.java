package org.gxg.sort;

import org.gxg.tools.In;

public class Insertion extends Common {
    public static void sort(Comparable[] a) {
        init_counter();

        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }

        assert isSorted(a);
        print_counter();
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
