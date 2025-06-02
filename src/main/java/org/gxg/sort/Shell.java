package org.gxg.sort;

import org.gxg.tools.In;

public class Shell extends Common {
    public static void sort(Comparable[] a) {
        init_counter();
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
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
