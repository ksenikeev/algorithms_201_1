package ru.itis.algorithms_201_1.asadullin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static term2.AaDS.SemesterWork.GraphGenerator.getRandomInt;

public class GraphGenerating {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/term2/AaDS/SemesterWork/graphs/graph_";

        for (int i = 1; i < 101; i++) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + i + ".txt", true));
            int[][] newGraph = GraphGenerator.generate(100 + 20 * i + getRandomInt(1, 20));

            writer.write(GraphGenerator.graphToString(newGraph));
            writer.flush();
            writer.close();
        }
    }
}
