package org.gxg.sort;

import org.gxg.tools.StdRandom;
import org.gxg.tools.Stopwatch;

public class InsertionForInt {

    public static void sort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    // is v < w ?
    private static boolean less(int v, int w) {
        return v < w;
    }

    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // print array to standard output
    private static void show(int[] a) {
        for (int j : a) System.out.println(j);
    }

    private static int[] randomInt(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(1000000000);
        }
        return a;
    }

    private static Integer[] randomInteger(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(1000000000);
        }
        return a;
    }

    public static void main(String[] args) {
        // 比较 int 和 Integer 的性能差异

        int n = 10000;
        double time;

        System.out.println("............sort name is InsertionForInt..........");
        int[] a = randomInt(n);
        Stopwatch timer1 = new Stopwatch();
        InsertionForInt.sort(a);
        time = timer1.elapsedTime();
        System.out.printf("for %d random int (%.2f seconds)\n", n, time);

        System.out.println();
        System.out.println("............sort name is Insertion..........");
        Integer[] b = randomInteger(n);
        Stopwatch timer2 = new Stopwatch();
        Insertion.sort(b);
        time = timer2.elapsedTime();
        System.out.printf("for %d random int (%.2f seconds)\n", n, time);

    }
}
