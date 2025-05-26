package org.gxg.sort;

import org.gxg.tools.In;

public class SelectionForBig2Small {
    // TODO

    public static void sort(Comparable[] a) {
        // TODO
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
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
