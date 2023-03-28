package ru.itis.algorithms_201_1.paramonov;

import java.util.Random;

public class GraphGenerator {
    private final Random random = new Random();
    private final int MAX_VALUE = 100;
    private final int MIN_VALUE = -100;

    private final int minSize = 10;

    private final int maxSize = 100;

    private final double CHANCE_TO_BE_NULL = 0.2;


    public Integer[][] generate() {
        int vertexCount = random.nextInt(minSize, maxSize + 1);
        Integer[][] result = new Integer[vertexCount][vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (random.nextDouble() > CHANCE_TO_BE_NULL) {
                    result[i][j] = random.nextInt(MIN_VALUE, MAX_VALUE + 1);
                }
            }
        }
        return result;
    }
}

