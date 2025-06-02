package org.gxg.sort;

import org.gxg.tools.In;

public class Selection extends Common {

    public static void sort(Comparable[] a) {
        init_counter();

        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
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
