package org.gxg.app.sort;

import org.gxg.sort.*;
import org.gxg.tools.In;
import org.gxg.tools.Stopwatch;

public class SortCompareWithString {

    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("Quick")) Quick.sort(a);
        if (alg.equals("Quick3way")) Quick3way.sort(a);
        if (alg.equals("QuickBentleyMcIlroy")) QuickBentleyMcIlroy.sort(a);
        if (alg.equals("QuickGxg")) QuickGxg.sort(a);
        if (alg.equals("Merge")) Merge.sort(a);
        if (alg.equals("MergeBU")) MergeBU.sort(a);
        return timer.elapsedTime();
    }

    private static String[] readWords() {
        String file = "testData/sort_large_words.txt";
//        String file = "testData/sort_large_same_words.txt";
//        String file = "testData/sort_words.shakespeare.txt";
        In in = new In(file);
        String[] a = in.readAllStrings();
        in.close();
        return a;
    }

    public static void main(String[] args) {
        String[] sorts = {
                "Quick"
                , "Quick3way"
                , "QuickBentleyMcIlroy"
                , "QuickGxg"
                , "Merge"
                , "MergeBU"
                , "Shell"
//                , "Insertion"
//                , "Selection"
        };
//        String[] sorts = {"Quick"};

        for(String alg : sorts) {
            String[] a = readWords();
            // 打乱顺序
//            StdRandom.shuffle(a);

            System.out.println("............sort name is: " + alg + "..........");
            double time = time(alg, a);
            System.out.printf("for %d random Strings (%.2f seconds)\n", a.length, time);

            System.out.println();
        }
    }
}
