package ru.itis.algorithms_201_1.asadullin;

import java.util.HashSet;
import java.util.Random;
import java.util.stream.IntStream;

public class GraphGenerator {
    public static int[][] generate(int verticesCount) {
        return generate(verticesCount, 5, 20, 2);
    }

    public static int[][] generate(int verticesCount, int minWeight, int maxWeight, int minNeighbour) {
        int[][] weights = new int[verticesCount][verticesCount];

        HashSet<Integer> vertices = new HashSet<>();
        IntStream.rangeClosed(0, verticesCount - 1).forEach(vertices::add);
        HashSet<Integer> allVertices = new HashSet<>(vertices);

        while (vertices.size() > 0) {
            int randomVertex = getRandomElement(vertices);
            int adjacentVerticesCount = getRandomInt(minNeighbour, allVertices.size() - 1);
            HashSet<Integer> possibleAdjacent = new HashSet<>(allVertices);

            vertices.remove(randomVertex);
            possibleAdjacent.remove(randomVertex);

            for (int i = 0; i < adjacentVerticesCount; i++) {
                int randomAdjacent = getRandomElement(possibleAdjacent);
                int randomWeight = getRandomInt(minWeight, maxWeight);
                weights[randomVertex][randomAdjacent] += randomWeight;
                possibleAdjacent.remove(randomAdjacent);

            }
        }

        return weights;
    }

    private static int getRandomElement(HashSet<Integer> vertices) {
        int size = vertices.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for (Integer vertex : vertices) {
            if (i == item) return vertex;
            i++;
        }

        return 0;
    }

    static int getRandomInt(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static String graphToString(int[][] graph) {
        StringBuilder graphString = new StringBuilder();

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] == 999999999) graphString.append("INF\t");
                else graphString.append(graph[i][j]).append("\t");
            }
            graphString.append("\n");
        }

        return graphString.toString();
    }
}
