import java.util.Arrays;

public class BellmanFordAlgorithm {
    private Graph graph;

    private double[] paths;

    private void createInitialMatrix(Graph graph, int source) {
        int vertexCount = graph.getVertexCount();
        paths = new double[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            paths[i] = Double.POSITIVE_INFINITY;
        }
        paths[source - 1] = 0;
    }

    private void getPaths() {
        int vertexCount = graph.getVertexCount();
        for (int i = 1; i < vertexCount; i++) {
            checkEdges(paths);
        }
    }
    private boolean hasNegativeCycle() {
        double[] pathsToCheck = Arrays.copyOf(paths, paths.length);
        checkEdges(pathsToCheck);
        return !(Arrays.equals(pathsToCheck, paths));
    }

    private void checkEdges(double[] arr) {
        for (Graph.Edge edge : graph.getEdges()) {
            double initial = arr[edge.getDestination()];
            double changed = arr[edge.getStart()] + edge.getValue();
            int comparison = Double.compare(initial, changed);
            if (comparison > 0) {
                arr[edge.getDestination()] = changed;
            }
        }
    }

    public void check(Integer[][] matrix, int source) {
        Graph graph = new Graph(matrix);
        this.graph = graph;
        createInitialMatrix(graph, source);
        getPaths();
        if (hasNegativeCycle()) {
            System.out.println("The graph has negative cycle.");
            return;
        }
        System.out.println(Arrays.toString(paths));
    }
}
