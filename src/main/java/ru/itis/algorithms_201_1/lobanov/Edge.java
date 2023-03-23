package ru.itis.algorithms_201_1.lobanov;

public class Edge implements Comparable<Edge> {
    private final int node1;
    private final int node2;
    private final int weight;

    public Edge(int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if ((node1 != edge.node1 || node2 != edge.node2) &&
                (node1 != edge.node2 || node2 != edge.node1)) return false;
        return weight == edge.weight;
    }

    @Override
    public String toString() {
        return "Edge{" + node1 + "-" + node2 + " : " + weight + '}';
    }
}
