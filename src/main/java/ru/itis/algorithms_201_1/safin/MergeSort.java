package ru.itis.algorithms_201_1.safin;

public class MergeSort {
    public static int sort(int[] a, int n) {
        int iterations = 0;
        if (n < 2) {
            return iterations;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            iterations++;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
            iterations++;
        }
        iterations += sort(l, mid);
        iterations += sort(r, n - mid);

        iterations = merge(a, l, r, mid, n - mid, iterations);
        return iterations;
    }

    public static int merge(int[] a, int[] l, int[] r, int left, int right, int iterations) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
            iterations++;
        }
        while (i < left) {
            a[k++] = l[i++];
            iterations++;
        }
        while (j < right) {
            a[k++] = r[j++];
            iterations++;
        }
        return iterations;
    }
}
