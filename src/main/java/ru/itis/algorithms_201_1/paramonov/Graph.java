package ru.itis.algorithms_201_1.paramonov;

import java.util.ArrayList;

public class Graph {
    public class Edge {
        //initial vertex
        int u;
        //destination vertex
        int v;

        int value;

        public Edge(int u, int v, int value) {
            this.u = u;
            this.v = v;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public int getStart() {
            return u;
        }
        public int getDestination() {
            return v;
        }
    }

    public Graph(Integer[][] graph) {
        edges = new ArrayList<>();
        vertexCount = graph.length;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] != null) {
                    Edge edge = new Edge(i, j, graph[i][j]);
                    edges.add(edge);
                }
            }
        }
    }

    private ArrayList<Edge> edges;
    private int vertexCount;

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}

