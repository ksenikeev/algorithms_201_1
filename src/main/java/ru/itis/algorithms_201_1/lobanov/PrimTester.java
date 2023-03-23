package ru.itis.algorithms_201_1.lobanov;

import ru.itis.algorithms_201_1.lobanov.dataFiles.DataFilesGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimTester {
    public static void main(String[] args) {
        try {
            final int AMOUNT_OF_FILES = 100;
            final int AMOUNT_OF_REPEATS = 15;
            final String MEASUREMENTS_INFO = "Data Time Iterations";
            final String ADJACENCY_MATRIX_ALGORITHM_FILE_NAME = "AdjacencyMatrixAlgorithmResults.txt";
            final String BINARY_HEAP_ALGORITHM_FILE_NAME = "BinaryHeapAlgorithmResults.txt";
            final String SOURCE = "src\\main\\java\\ru\\itis\\algorithms_201_1\\lobanov\\DataFiles\\";

            List<String> adjacencyMatrixResults = new ArrayList<>();
            List<String> binaryHeapResults = new ArrayList<>();
            adjacencyMatrixResults.add(MEASUREMENTS_INFO);
            binaryHeapResults.add(MEASUREMENTS_INFO);

            for (int i = 1; i <= AMOUNT_OF_FILES; i++) {
                final String INPUT_FILE = SOURCE + "TestFile" + i + ".txt";
                Path path = Paths.get(INPUT_FILE);
                int amountOfNumbers = (int) Files.lines(path).count();
                int[][] G;
                int data = 0;
                long adjacencyMatrixIterations = 0;
                long adjacencyMatrixTime = 0;
                long binaryHeapIterations = 0;
                long binaryHeapTime = 0;
                DataFilesGenerator dataFilesGenerator = new DataFilesGenerator();
                for (int j = 0; j < AMOUNT_OF_REPEATS; j++) {
                    dataFilesGenerator.generateFiles();
                    Scanner scanner = new Scanner(new File(INPUT_FILE));
                    int amountOfNodes = 2 * amountOfNumbers;
                    amountOfNodes = (int) Math.sqrt(amountOfNodes);
                    amountOfNodes += 1;
                    G = new int[amountOfNodes][amountOfNodes];
                    boolean[][] J = new boolean[amountOfNodes][amountOfNodes];
                    AdjacencyList list = new AdjacencyList(amountOfNodes);

                    int count = 0;
                    while (scanner.hasNext()) {
                        int num = scanner.nextInt();
                        if (count / amountOfNodes == count % amountOfNodes) count++;
                        int node1 = count / amountOfNodes;
                        int node2 = count % amountOfNodes;
                        list.addEdge(node1, node2, num);
                        G[node1][node2] = num;
                        if (G[node2][node1] == 0) {
                            G[node2][node1] = num;
                        }
                        J[node1][node2] = true;
                        J[node2][node1] = true;
                        count++;
                        while (J[count / amountOfNodes][count % amountOfNodes]) count++;
                    }

                    Measurement binaryHeapPrimMeasurement = new Measurement(amountOfNumbers);
                    BinaryHeapPrim binaryHeapPrim = new BinaryHeapPrim(list.getAdjacencyList(), binaryHeapPrimMeasurement);
                    binaryHeapIterations += binaryHeapPrimMeasurement.getIterations();
                    binaryHeapTime += binaryHeapPrimMeasurement.getTime();

                    Measurement adjacencyMatrixPrimMeasurement = new Measurement(amountOfNumbers);
                    AdjacencyMatrixPrim adjacencyMatrixPrim = new AdjacencyMatrixPrim(G, adjacencyMatrixPrimMeasurement);
                    adjacencyMatrixIterations += adjacencyMatrixPrimMeasurement.getIterations();
                    adjacencyMatrixTime += adjacencyMatrixPrimMeasurement.getTime();

                    data = adjacencyMatrixPrimMeasurement.getData();

                }
                adjacencyMatrixIterations = adjacencyMatrixIterations / AMOUNT_OF_REPEATS;
                adjacencyMatrixTime = adjacencyMatrixTime / AMOUNT_OF_REPEATS;
                adjacencyMatrixResults.add(data + " " + adjacencyMatrixTime + " " + adjacencyMatrixIterations);

                binaryHeapIterations = binaryHeapIterations / AMOUNT_OF_REPEATS;
                binaryHeapTime = binaryHeapTime / AMOUNT_OF_REPEATS;
                binaryHeapResults.add(data + " " + binaryHeapTime + " " + binaryHeapIterations);
                System.out.println(data + " " + adjacencyMatrixTime + " " + adjacencyMatrixIterations);
            }

            String adjacencyMatrixFileName = SOURCE + ADJACENCY_MATRIX_ALGORITHM_FILE_NAME;
            String binaryHeapFileName = SOURCE + BINARY_HEAP_ALGORITHM_FILE_NAME;
            Files.write(Paths.get(adjacencyMatrixFileName), adjacencyMatrixResults);
            Files.write(Paths.get(binaryHeapFileName), binaryHeapResults);

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
