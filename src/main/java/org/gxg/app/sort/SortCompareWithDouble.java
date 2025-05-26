package org.gxg.app.sort;

import org.gxg.sort.*;
import org.gxg.tools.StdRandom;
import org.gxg.tools.Stopwatch;

public class SortCompareWithDouble {

    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("Quick")) Quick.sort(a);
        if (alg.equals("Merge")) Merge.sort(a);
        return timer.elapsedTime();
    }

    private static Double[] randomDoubles(int n) {
        Double a[] = new Double[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformDouble();
        }
        return a;
    }

    public static double timeRandomInput(String alg, int N) {
        return time(alg, randomDoubles(N));
    }

    public static void main(String[] args) {
        String[] sorts = {
                "Shell"
                , "Selection"
                , "Quick"
                , "Insertion"
                , "Merge"
        };
//        String[] sorts = {"Quick"};
        for(String alg : sorts) {
            System.out.println("............sort name is: " + alg + "..........");
            double time = timeRandomInput(alg, 1000);
            System.out.printf("for %d random Doubles (%.2f seconds)\n", 1000, time);

            time = timeRandomInput(alg, 10000);
            System.out.printf("for %d random Doubles (%.2f seconds)\n", 10000, time);

            time = timeRandomInput(alg, 100000);
            System.out.printf("for %d random Doubles (%.2f seconds)\n", 100000, time);
            System.out.println();
        }
    }
}
