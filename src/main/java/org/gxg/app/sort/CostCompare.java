package org.gxg.app.sort;

import org.gxg.tools.StdStats;
import org.gxg.tools.Stopwatch;

public class CostCompare {
    public static double foreachDoNothing(int n) {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

            }
        }
        return timer.elapsedTime();
    }

    @SuppressWarnings("unchecked")
    public static double lessArray(int n) {
        Comparable[] arr = new Comparable[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Comparable v = arr[i];
                v.compareTo(arr[n-1]);// 这个语句成本较高
            }
        }
        return timer.elapsedTime();
    }

    @SuppressWarnings("unchecked")
    public static double exchArray(int n) {
        Comparable[] arr = new Comparable[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Comparable v = arr[i];
                arr[i] = arr[n-1];
                arr[n-1] = v;
            }
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {

        int n;
        int test_times = 1;
        n = 100000;
        double[] test_durations = new double[test_times];
        for (int i = 0; i < test_times; i++) {
            test_durations[i] = foreachDoNothing(n);
        }
        System.out.printf("%d x %d foreachDoNothing, %d times mean duration: %f", n, n, test_times, StdStats.mean(test_durations));
        System.out.println();


        for (int i = 0; i < test_times; i++) {
            test_durations[i] = lessArray(n);
        }
        System.out.printf("%d x %d x 2 lessArray, %d times mean duration: %f", n, n, test_times, StdStats.mean(test_durations));
        System.out.println();


        for (int i = 0; i < test_times; i++) {
            test_durations[i] = exchArray(n);
        }
        System.out.printf("%d x %d exchArray, %d times mean duration: %f", n, n, test_times, StdStats.mean(test_durations));
        System.out.println();
    }
}
