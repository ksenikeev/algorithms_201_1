package ru.itis.algorithms_201_1.lobanov;

import java.util.Random;

public class AdjacencyMatrixPrim extends PrimAlgorithm {
    private final int[][] G;
    private final Random random;
    private final Measurement adjacencyMatrixPrimMeasurement;

    public AdjacencyMatrixPrim(int[][] G, Measurement adjacencyMatrixPrimMeasurement) {
        this.G = G;
        super.numOfV = G.length;
        this.adjacencyMatrixPrimMeasurement = adjacencyMatrixPrimMeasurement;
        this.random = new Random();
        super.MST = buildMST();
    }

    private int[][] buildMST() {
        final int INFINITE = Integer.MAX_VALUE;

        int[][] MST = new int[numOfV][numOfV];
        boolean[] selectedNodes = new boolean[numOfV];
        int node = random.nextInt(numOfV);
        selectedNodes[node] = true;
        int countOfEdges = 0;

        adjacencyMatrixPrimMeasurement.startMeasurement();
        while (countOfEdges < numOfV - 1) {
            adjacencyMatrixPrimMeasurement.addIteration();
            int minEdge = INFINITE;
            int U = 0;
            int V = 0;
            for (int u = 0; u < numOfV; u++) {
                adjacencyMatrixPrimMeasurement.addIteration();
                if (selectedNodes[u]) {
                    for (int v = 0; v < numOfV; v++) {
                        adjacencyMatrixPrimMeasurement.addIteration();
                        if (!selectedNodes[v] && G[u][v] != 0) {
                            if (minEdge >= G[u][v]) {
                                minEdge = G[u][v];
                                U = u;
                                V = v;
                            }
                        }
                    }
                }
            }
            MST[U][V] = minEdge;
            selectedNodes[V] = true;
            countOfEdges++;
        }
        adjacencyMatrixPrimMeasurement.stopMeasurement();
        return MST;
    }

    public Measurement getAdjacencyMatrixPrimMeasurement() {
        return adjacencyMatrixPrimMeasurement;
    }
}
