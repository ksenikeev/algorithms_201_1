package ru.itis.algorithms_201_1.lobanov;

import java.util.Arrays;
import java.util.Random;

public class AdjacencyMatrixPrim {
    private final int[][] G;
    private final int numOfV;
    private int[][] MST;
    private final Random random;

    public AdjacencyMatrixPrim(int[][] G) {
        this.G = G;
        this.numOfV = G.length;
        this.random = new Random();
        MST = buildMST();
    }

    public void printMST() {
        StringBuilder sb = new StringBuilder();
        sb.append("First Node - Second Node : Edge's Weight\n");
        for (int u = 0; u < numOfV; u++) {
            for (int v = 0; v < numOfV; v++) {
                int edge = MST[u][v];
                if (edge != 0) {
                    sb.append(u);
                    sb.append(" - ");
                    sb.append(v);
                    sb.append(" : ");
                    sb.append(edge);
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    public void printMSTMatrix() {
        buildMSTMatrix(MST);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfV; i++) {
            sb.append(Arrays.toString(MST[i]));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private int[][] buildMST() {
        final int INFINITE = Integer.MAX_VALUE;

        int[][] MST = new int[numOfV][numOfV];
        boolean[] selectedNodes = new boolean[numOfV];
        int node = random.nextInt(numOfV);
        selectedNodes[node] = true;
        int countOfEdges = 0;

        while (countOfEdges < numOfV - 1) {
            int minEdge = INFINITE;
            int U = 0;
            int V = 0;
            for (int u = 0; u < numOfV; u++) {
                if (selectedNodes[u]) {
                    for (int v = 0; v < numOfV; v++) {
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
        return MST;
    }

    private void buildMSTMatrix(int[][] MST) {
        for (int u = 0; u < numOfV; u++) {
            for (int v = 0; v < numOfV; v++) {
                if (MST[u][v] != 0) {
                    if (MST[v][u] == 0) {
                        MST[v][u] = MST[u][v];
                    }
                }
            }
        }
    }
}
