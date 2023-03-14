package ru.itis.algorithms_201_1.ponomarev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ShellSorter {
    public static void main(String[] args) throws IOException {
        Path p = Paths.get("C:\\Users\\ipono\\IdeaProjects\\algorithms_201_1\\src\\main\\java\\ru\\itis\\algorithms_201_1\\ponomarev\\res\\input_001");
        int[] arr = Files.lines(p)
                .mapToInt(Integer::parseInt)
                .toArray();

        SortingStats stats = sort(arr);
        System.out.println(stats);
    }

    public static SortingStats sort(int[] arr) {
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
        return new SortingStats(timeTaken, iterations);
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
