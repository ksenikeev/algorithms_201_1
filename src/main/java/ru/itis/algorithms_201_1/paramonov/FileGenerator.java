package ru.itis.algorithms_201_1.paramonov;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class FileGenerator {
    public void generateFile(int index) {
        String path = "files/test" + index + ".txt";
        try {
            Path file = Files.createFile(Paths.get(path));
            GraphGenerator generator = new GraphGenerator();
            Integer[][] matrix = generator.generate();
            String line;
            for (int i = 0; i < matrix.length; i++) {
                line = Arrays.toString(matrix[i]);
                line = line.substring(1, line.length() - 1) + "\n";
                Files.writeString(file, line, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
        }
    }
}
