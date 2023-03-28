package ru.itis.algorithms_201_1.bagaviev;

public class DataGenerator {

    public int generateInt(int from, int to) {
        return (int)(Math.random() * (from - to)) + to;
    }

    public int[] generateIntValues(int from, int to, int count) {
        int[] data = new int[count];
        for (int i = 0; i < count; i++) {
            data[i] = generateInt(from, to);
        }
        return data;
    }

}
