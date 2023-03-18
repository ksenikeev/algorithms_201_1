package ru.itis.algorithms_201_1.ponomarev;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides sort method, which uses Shell Sort algorithm.
 */
public class ShellSorter {
    private SortingStats sortingStats;

    /**
     * Sorts provided array in natural order.<br>
     * @implNote This implementation uses Shell Sort with Shell's original gap iterator, which provides <code>O(n^2)</code> time complexity.
     * @param arr an array to sort.
     */
    public void sort(int[] arr) {
        Iterator<Integer> gapIter = new GapIterator(arr.length);

        long iterations = 0;
        long timeStart = System.nanoTime();

        while (gapIter.hasNext()) {
            int d = gapIter.next();
            int j;
            for (int i = d; i < arr.length; i += d) {
                int swap = arr[i];
                for (j = i; j > 0 && swap < arr[j-d]; j -= d) {
                    arr[j] = arr[j-d];
                }
                arr[j] = swap;

                iterations++;
            }
        }

        long timeTaken = System.nanoTime() - timeStart;
        sortingStats = new SortingStats(arr.length, timeTaken, iterations);
    }

    /**
     * Returns stats of previous sorting.
     * @return stats of previous sorting; if {@link #sort(int[])} wasn't called yet, returns null.
     */
    public SortingStats getSortingStats() {
        return sortingStats;
    }

    private static class GapIterator implements Iterator<Integer> {
        private int d;

        public GapIterator(int n) {
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
}
