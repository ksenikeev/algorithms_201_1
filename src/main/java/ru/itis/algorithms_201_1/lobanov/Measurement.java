package ru.itis.algorithms_201_1.lobanov;

public class Measurement {
    private int data;
    private long startTime;
    private long endTime;
    private long iterations;

    public Measurement(int data) {
        this.data = data;
        this.iterations = 0;
    }

    public void addIteration() {
        iterations++;
    }

    public void addIteration(int iterations) {
        this.iterations += iterations;
    }

    public void reduceIteration() {
        iterations--;
    }
    public void startMeasurement() {
        startTime = System.nanoTime();
    }

    public void stopMeasurement() {
        endTime = System.nanoTime();
    }

    public long getIterations() {
        return iterations;
    }

    public long getTime() {
        return endTime - startTime;
    }

    public int getData() {
        return data;
    }

    public String getInfo() {
        return "" + data + " " + getTime() + " " + getIterations();
    }
}
