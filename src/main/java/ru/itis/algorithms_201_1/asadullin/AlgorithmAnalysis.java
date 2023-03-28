package ru.itis.algorithms_201_1.asadullin;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

import static term2.AaDS.SemesterWork.FloydWarshallAlgorithm.floydWarshall;
import static term2.AaDS.SemesterWork.GraphGenerator.graphToString;

public class AlgorithmAnalysis {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/term2/AaDS/SemesterWork/graphs/graph_";
        BufferedWriter complexityWriter = new BufferedWriter(new FileWriter("src/main/java/term2/AaDS/SemesterWork/graphs/complexity.txt", true));

        for (int i = 1; i < 101; i++) {
            BufferedReader reader = new BufferedReader(new FileReader(filePath + i + ".txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + i + "_result.txt", true));

            int[][] initialGraph = readGraph(reader);
            ArrayList<Object> result = floydWarshall(initialGraph.clone());

            int[][] resultGraph = (int[][]) result.get(0);
            long timeComplexity = (long) result.get(1);
            long operations = (long) result.get(2);

            writer.write("Result graph:\n");
            writer.write(graphToString(resultGraph) + "\n\n");

            complexityWriter.write(resultGraph.length + "\t" + new BigInteger(String.valueOf(timeComplexity)) + "\t" + new BigInteger(String.valueOf(operations)) + "\n");
            writer.write("Time complexity: " + timeComplexity + " ns.\n");
            writer.write("Iteration complexity: O(n^" + Math.log(operations) / Math.log(resultGraph.length) + ")\n");

            writer.flush();
            complexityWriter.flush();
            writer.close();
        }

        complexityWriter.close();
    }

    private static int[][] readGraph(BufferedReader reader) throws IOException {
        String[] graphRow = reader.readLine().split("\t");
        int graphSize = graphRow.length;
        int[][] graph = new int[graphSize][graphSize];

        for (int i = 0; i < graphSize; i++) {
            if (graphRow[i].equals("INF")) graph[0][i] = 999999999;
            else graph[0][i] = Integer.parseInt(graphRow[i]);
        }

        for (int i = 1; i < graphSize; i++) {
            graphRow = reader.readLine().split("\t");
            for (int j = 0; j < graphSize; j++) {
                if (graphRow[j].equals("INF")) graph[i][j] = 999999999;
                else graph[i][j] = Integer.parseInt(graphRow[j]);
            }
        }

        return graph;

    }
}
