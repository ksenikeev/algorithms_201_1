package ru.itis.algorithms_201_1.ponomarev;

public class SortingStats {
    final private int dataSize;
    final private long nanoTimeTaken;
    final private long iterationsCount;

    public SortingStats(int dataSize, long nanoTimeTaken, long iterationsCount) {
        this.dataSize = dataSize;
        this.nanoTimeTaken = nanoTimeTaken;
        this.iterationsCount = iterationsCount;
    }

    public int getDataSize() {
        return dataSize;
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
                "dataSize=" + dataSize +
                ", nanoTimeTaken=" + nanoTimeTaken +
                ", iterationsCount=" + iterationsCount +
                '}';
    }
}
