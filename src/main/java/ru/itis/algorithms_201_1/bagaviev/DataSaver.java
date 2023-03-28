package ru.itis.algorithms_201_1.bagaviev;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataSaver {

    private static final Path dataPath = Paths.get("src/main/" +
            "java/ru/itis/algorithms_201_1/" +
            "bagaviev/res/data.txt");

    private static final String VALUES_SEPARATOR = "; ";

    private static final int SETS_COUNT = 100;

    public static void renderData() throws IOException {

        PrintWriter printWriter = new PrintWriter(dataPath.toFile());
        DataGenerator dataGenerator = new DataGenerator();

        for (int i = 1; i <= SETS_COUNT; i++) {

            int[] data = dataGenerator.generateIntValues(-100_000_000, 100_000_000, SETS_COUNT * i);

            printWriter.printf("Set %d, values count: %d:\n", i, SETS_COUNT * i);
            for (int value : data) {
                printWriter.print(value + VALUES_SEPARATOR);
            }

            printWriter.println();

        }
        printWriter.close();
    }

    public static void main(String[] args) {
        try {
            renderData();
        } catch (IOException exception) {
            throw new RuntimeException("Failed to open file", exception);
        } finally {
            System.out.println("Data was successfully saved to file");
        }
    }

}
