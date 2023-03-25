package ru.itis.algorithms_201_1.arifulina;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class KraskalAlgorithm {
    public static void main(String[] args){
        writeTestData();
        getResultData();
    }
    public final static int AMOUNT_OF_TESTS = 100;
    public final static int START_AMOUNT_OF_VERTICES = 50;
    public final static int STEP = 5;
    public static long timeOfExecutingNanoSeconds;
    public static long iterations;

    public static SimpleWeighedGraph kraskalAlgorithm(SimpleWeighedGraph graph){

        long start = System.nanoTime();

        int n = graph.vertices.size();
        int countOfVertices = 0;
        int m = 0;
        int i = 0;
        SimpleWeighedGraph ostov = new SimpleWeighedGraph(n);
        boolean[] currVertices = new boolean[n];

        MergeSort.mergeSort(graph.edges);

        while (m < n - 1 && i < graph.edges.size()){
            if (!currVertices[graph.edges.get(i).inc1]){
                currVertices[graph.edges.get(i).inc1] = true;
                countOfVertices++;
            }
            if (!currVertices[graph.edges.get(i).inc2]){
                currVertices[graph.edges.get(i).inc2] = true;
                countOfVertices++;
            }
            if (m < countOfVertices - 1){
                ostov.edges.add(graph.edges.get(i));
                m++;
            }
            i++;
        }

        long end = System.nanoTime();

        timeOfExecutingNanoSeconds = end - start;
        iterations = MergeSort.iterations + i;
        return ostov;
    }

    public static int[][] getWeighedGraph(int n){
        int[][] table = new int[n][n];
        int x;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (table[i][j] == 0) {
                    x = SimpleWeighedGraph.RAND.nextInt(0, 2)
                            * SimpleWeighedGraph.RAND.nextInt(1, 101);
                    table[i][j] = x;
                    table[j][i] = x;
                }
            }
        }
        return table;
    }

    public static void writeTestData(){
        int n = START_AMOUNT_OF_VERTICES;
        for (int i = 1; i <= AMOUNT_OF_TESTS; i++) {
            File file = new File("test_data", i + ".txt");
            try (FileWriter writer = new FileWriter(file, false)) {
                SimpleWeighedGraph graph = new SimpleWeighedGraph(getWeighedGraph(n));
                graph.toConnected();
                for (SimpleWeighedGraph.Edge edge : graph.edges) {
                    writer.write(edge.toString() + "\n");
                }
                n += STEP;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static void getResultData(){
        int n = START_AMOUNT_OF_VERTICES;
        for (int i = 1; i <= AMOUNT_OF_TESTS; i++){
            Path path = Paths.get("test_data\\" + i + ".txt");
            try (Stream<String> edges = Files.lines(path)){
                SimpleWeighedGraph graph = new SimpleWeighedGraph(n);
                edges.forEach(edge -> graph.addEdge(edge.split(" ")));

                timeOfExecutingNanoSeconds = 0;
                iterations = 0;
                MergeSort.iterations = 0;
                kraskalAlgorithm(graph);
                try (FileWriter writer = new FileWriter("results.txt", true)) {
                    writer.write(graph.edges.size() + " " + timeOfExecutingNanoSeconds + " " + iterations + "\n");
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

                n += STEP;
            } catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
