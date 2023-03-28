package ru.itis.algorithms_201_1.bagaviev;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import static ru.itis.algorithms_201_1.bagaviev.BucketSort.bucketSort;

public class Test {

    private static final Path dataPath = Paths.get("src/main/" +
            "java/ru/itis/algorithms_201_1/" +
            "bagaviev/res/data.txt");

    private static final Path resultsPath = Paths.get("src/main/" +
            "java/ru/itis/algorithms_201_1/" +
            "bagaviev/res/results.txt");

    private static final String VALUES_SEPARATOR = "; ";

    public static void getBucketSortingResults() throws IOException {
        PrintWriter printWriter = new PrintWriter(resultsPath.toAbsolutePath().toFile());
        Scanner scanner = new Scanner(dataPath.toAbsolutePath().toFile());

        long end, start;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("Set")) line = scanner.nextLine();
            int[] data = Arrays
                    .stream(line.split(VALUES_SEPARATOR))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            start = System.nanoTime();
            int iterationCount = bucketSort(data);
            end = System.nanoTime();
            printWriter.println((end - start) +" - " + iterationCount);
        }
        printWriter.close();
        scanner.close();
    }

    public static void main(String[] args) {
        try {
            getBucketSortingResults();
        } catch (IOException exception) {
            throw new RuntimeException("Failed to open file", exception);
        }
    }


}
