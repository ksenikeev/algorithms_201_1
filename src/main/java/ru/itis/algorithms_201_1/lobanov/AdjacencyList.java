package ru.itis.algorithms_201_1.lobanov;

import java.util.ArrayList;

public class AdjacencyList {
    private final int numOfNodes;
    private final ArrayList<Edge>[] adjacencyList;

    public AdjacencyList(int numOfNodes) {
        this.numOfNodes = numOfNodes;
        adjacencyList = new ArrayList[numOfNodes];
        for (int i = 0; i < numOfNodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int node1, int node2, int weight) {
        Edge edge = new Edge(node1, node2, weight);
        adjacencyList[node1].add(edge);

        edge = new Edge(node2, node1, weight);
        adjacencyList[node2].add(edge);
    }

    public ArrayList<Edge>[] getAdjacencyList() {
        return adjacencyList;
    }
}
