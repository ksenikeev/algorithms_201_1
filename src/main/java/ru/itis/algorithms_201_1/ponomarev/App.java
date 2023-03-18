package ru.itis.algorithms_201_1.ponomarev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        // list of lines to write to csv file
        List<String> csvLines = new LinkedList<>();
        csvLines.add("Data Size,Time,Iterations");

        ShellSorter sorter = new ShellSorter();

        for (int i = 1; i <= 100; i++) {
            String number = String.format("%1$3s", i + "").replace(' ', '0'); // left pad with zeros
            Path path = Paths.get("C:\\Users\\ipono\\IdeaProjects\\algorithms_201_1\\src\\main\\java\\ru\\itis\\algorithms_201_1\\ponomarev\\res\\input_" + number);

            // run sort on one input 30 times and save stats with minimum time
            SortingStats stats = null;
            for (int j = 0; j < 30; j++) {
                int[] arr = Files.lines(path)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                sorter.sort(arr);
                SortingStats tmp = sorter.getSortingStats();
                if (stats == null || tmp.getNanoTimeTaken() < stats.getNanoTimeTaken()) {
                    stats = tmp;
                }
            }

            csvLines.add(stats.toCSVString());
        }

        // write to csv
        Path statsPath = Paths.get("C:\\Users\\ipono\\IdeaProjects\\algorithms_201_1\\src\\main\\java\\ru\\itis\\algorithms_201_1\\ponomarev\\res\\output.csv");
        Files.write(statsPath, csvLines);
    }
}
