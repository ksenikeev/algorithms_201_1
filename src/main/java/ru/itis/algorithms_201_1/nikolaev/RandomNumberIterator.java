package ru.itis.algorithms_201_1.nikolaev;

import java.util.Iterator;

public class RandomNumberIterator implements Iterator<Integer> {
    private final int count;
    private final int abs;
    public final int[] data;

    public RandomNumberIterator(int count, int abs) {
        this.count = count;
        this.abs = abs;
        this.data = new int[count];
        for (int i = 0; i < count; i++) {
            if (hasNext()) {
                data[i] = next();
            }
        }
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(data[i]);
        }
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        return (int) (Math.random() * abs);
    }
}
