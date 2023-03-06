package ru.itis.algorithms_201_1.ponomarev;

import java.util.*;

public class ShellSorter {
    public static void main(String[] args) {
        Random rng = new Random();
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rng.nextInt(10000000);
        }
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int[] arr3 = Arrays.copyOf(arr, arr.length);

        Iterator<Integer> shellIter = new ShellGapIterator(arr.length);
        Iterator<Integer> hibbardIter = new HibbardGapIterator(arr.length);
        Iterator<Integer> sedgewickIter = new SedgewickGapIterator(arr.length);

        long t = System.nanoTime();
        sort(arr, shellIter);
        System.out.println(System.nanoTime() - t);

        t = System.nanoTime();
        sort(arr2, hibbardIter);
        System.out.println(System.nanoTime() - t);

        t = System.nanoTime();
        sort(arr3, sedgewickIter);
        System.out.println(System.nanoTime() - t);
    }

    public static void sort(int[] arr) {
        Iterator<Integer> gapIter = new HibbardGapIterator(arr.length);
        sort(arr, gapIter);
    }

    public static void sort(int[] arr, Iterator<Integer> gapIter) {
        while (gapIter.hasNext()) {
            int d = gapIter.next();
            int j;
            for (int i = d; i < arr.length; i += d) {
                int swap = arr[i];
                for (j = i; j > 0 && swap < arr[j-d]; j -= d) {
                    arr[j] = arr[j-d];
                }
                arr[j] = swap;
            }
        }
    }

    // O(n^2)
    private static class ShellGapIterator implements Iterator<Integer> {
        private int d;

        public ShellGapIterator(int n) {
            this.d = n / 2;
        }

        @Override
        public boolean hasNext() {
            return d != 0;
        }

        @Override
        public Integer next() {
            if (d == 0) throw new NoSuchElementException();

            int ret = d;
            d /= 2;
            return ret;
        }
    }

    // O(n^(3/2))
    private static class HibbardGapIterator implements Iterator<Integer> {
        private static final double LN2 = Math.log(2);
        private int i;

        public HibbardGapIterator(int n) {
            i = (int) (Math.log(n + 1) / LN2);
        }

        @Override
        public boolean hasNext() {
            return i != 0;
        }

        @Override
        public Integer next() {
            if (i == 0) throw new NoSuchElementException();

            return (int) Math.pow(2, i--) - 1;
        }
    }

    // O(n^(4/3))
    private static class SedgewickGapIterator implements Iterator<Integer> {
        Stack<Integer> gaps;

        public SedgewickGapIterator(int n) {
            gaps = new Stack<>();
            int last = 0;
            for (int i = 0; last < n; i++) {
                if (i % 2 == 0) {
                    last = (int) (9 * (Math.pow(2, i) - Math.pow(2, i / 2.0)) + 1);
                } else {
                    last = (int) (8 * Math.pow(2, i) - 6 * Math.pow(2, (i + 1) / 2.0) + 1);
                }

                if (last < n) gaps.push(last);
            }
        }

        @Override
        public boolean hasNext() {
            return !gaps.isEmpty();
        }

        @Override
        public Integer next() {
            return gaps.pop();
        }
    }
}
