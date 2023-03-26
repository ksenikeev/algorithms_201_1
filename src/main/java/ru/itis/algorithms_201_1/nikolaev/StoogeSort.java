package ru.itis.algorithms_201_1.nikolaev;

import java.util.Arrays;

public class StoogeSort {
    static long count = 0;

    static void sort(int a[], int lo, int hi) throws Exception {

        if (a[lo] > a[hi]) {
            int T = a[lo];
            a[lo] = a[hi];
            a[hi] = T;
            count += 3;
        }
        if (lo + 1 >= hi) {
            count++;
            return;
        }
        int third = (hi - lo + 1) / 3;
        count++;
        sort(a, lo, hi - third);
        sort(a, lo + third, hi);
        sort(a, lo, hi - third);

    }

    static void sort(int[] a) throws Exception {
        sort(a, 0, a.length - 1);
    }
}
