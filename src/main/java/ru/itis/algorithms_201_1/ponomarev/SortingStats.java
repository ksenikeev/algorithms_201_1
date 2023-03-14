package ru.itis.algorithms_201_1.ponomarev;

public class SortingStats {
    final private long nanoTimeTaken;
    final private long iterationsCount;

    public SortingStats(long nanoTimeTaken, long iterationsCount) {
        this.nanoTimeTaken = nanoTimeTaken;
        this.iterationsCount = iterationsCount;
    }

    public long getNanoTimeTaken() {
        return nanoTimeTaken;
    }

    public long getIterationsCount() {
        return iterationsCount;
    }

    @Override
    public String toString() {
        return "SortingStats{" +
                "nanoTimeTaken=" + nanoTimeTaken +
                ", iterationsCount=" + iterationsCount +
                '}';
    }
}
