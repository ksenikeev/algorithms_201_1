package ru.itis.algorithms_201_1.paramonov;

import java.util.Arrays;

public class BellmanFordAlgorithm {
    private long numOfIterations;
    private Graph graph;

    private double[] paths;

    private int numOfEdges;
    private int numOfVertexes;


    private void createInitialMatrix(Graph graph, int source) {
        int vertexCount = graph.getVertexCount();
        paths = new double[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            paths[i] = Double.POSITIVE_INFINITY;
            numOfIterations++;
        }
        paths[source - 1] = 0;
    }

    private void getPaths() {
        int vertexCount = graph.getVertexCount();
        for (int i = 1; i < vertexCount; i++) {
            checkEdges(paths);
            numOfIterations++;
        }
    }

    private boolean hasNegativeCycle() {
        double[] pathsToCheck = Arrays.copyOf(paths, paths.length);
        checkEdges(pathsToCheck);
        return !(Arrays.equals(pathsToCheck, paths));
    }

    private void checkEdges(double[] arr) {
        for (Graph.Edge edge : graph.getEdges()) {
            numOfIterations++;
            double initial = arr[edge.getDestination()];
            double changed = arr[edge.getStart()] + edge.getValue();
            int comparison = Double.compare(initial, changed);
            if (comparison > 0) {
                arr[edge.getDestination()] = changed;
            }
        }
    }

    public double[] getLengths(Integer[][] matrix, int source) throws NegativeCycleException{
        Graph graph = new Graph(matrix);
        numOfVertexes = graph.getVertexCount();
        numOfEdges = graph.getEdgeCount();

        this.graph = graph;
        createInitialMatrix(graph, source);
        getPaths();
        if (hasNegativeCycle()) throw new NegativeCycleException("The graph has negative cycle.");
        return paths;
    }

    public long getNumOfIterations() {
        return numOfIterations;
    }

    public long getComplexity() {
        return (long) numOfEdges * numOfVertexes;
    }
}
