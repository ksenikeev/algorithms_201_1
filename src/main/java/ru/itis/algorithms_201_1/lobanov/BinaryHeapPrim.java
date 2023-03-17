package ru.itis.algorithms_201_1.lobanov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BinaryHeapPrim {
    private final int[][] G;
    private final int numOfV;
    private int[][] MST;
    private final Random random;

    public BinaryHeapPrim(int[][] G) {
        this.G = G;
        this.numOfV = G.length;
        this.random = new Random();
        MST = buildMST();
    }

    private int[][] buildMST() {
        int[][] MST = new int[numOfV][numOfV];
        boolean[] selectedNodes = new boolean[numOfV];
        int node = random.nextInt(numOfV);
        selectedNodes[node] = true;
        BinaryHeap<Edge> binaryHeap = new BinaryHeap<>();
        BinaryHeap<Edge> bh = new BinaryHeap<>();

        for (int i = 0; i < numOfV; i++) {
            if (G[node][i] != 0) {
                binaryHeap.add(new Edge(node, i, G[node][i]));
                bh.add(new Edge(node, i, G[node][i]));
            }
        }
        List<Edge> list = new ArrayList<>();
        while (!binaryHeap.isEmpty()) {
            Edge edge = binaryHeap.peek();
            list.add(edge);
            int node2 = edge.getNode2();
            for (int i = 0; i < numOfV; i++) {
                if (G[node2][i] != 0) {
                    if (!selectedNodes[i]) {
                        binaryHeap.add(new Edge(node2, i, G[node2][i]));
                    } else {
                        binaryHeap.remove(new Edge(node2, i, G[node2][i]));
                    }
                }
            }
            selectedNodes[node2] = true;
        }

        for (int i = 0; i < list.size(); i++) {
            Edge edge = list.get(i);
            int node1 = edge.getNode1();
            int node2 = edge.getNode2();
            int weight = edge.getWeight();
            MST[node1][node2] = weight;
        }
        return MST;
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

    private boolean isUncheckedNode(boolean[] selectedNodes) {
        for (int i = 0; i < numOfV; i++) {
            if (!selectedNodes[i]) return true;
        }
        return false;
    }
}
