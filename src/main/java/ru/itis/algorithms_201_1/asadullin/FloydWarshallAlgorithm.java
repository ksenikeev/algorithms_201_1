package ru.itis.algorithms_201_1.asadullin;

import java.util.ArrayList;

public class FloydWarshallAlgorithm {
    public static ArrayList<Object> floydWarshall(int[][] matrix) {
        int[][] resultMatrix = getUpdateMatrix(matrix);
        ArrayList<Object> result = new ArrayList<>();
        long operations = 0;
        long start = System.nanoTime();

        int i, j, k;

        for (k = 0; k < resultMatrix.length; k++) {
            for (i = 0; i < resultMatrix.length; i++) {
                for (j = 0; j < resultMatrix.length; j++) {
                    resultMatrix[i][j] = Math.min(resultMatrix[i][j], resultMatrix[i][k] + resultMatrix[k][j]);
                    operations++;
                }
            }
        }

        long end = System.nanoTime();

        result.add(resultMatrix);
        result.add(end - start);
        result.add(operations);

        return result;
    }

    private static int[][] getUpdateMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j && matrix[i][j] == 0) matrix[i][j] = 999999999; // if vertices are not connected, make their edge "infinite" weights
            }
        }

        return matrix;
    }
}
