package ru.itis.algorithms_201_1.lobanov;

import java.util.Arrays;

public abstract class PrimAlgorithm {
    protected int numOfV;
    protected int[][] MST;

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
