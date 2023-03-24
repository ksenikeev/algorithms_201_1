package ru.itis.algorithms_201_1.gimaletdinova;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.itis.algorithms_201_1.gimaletdinova.SmoothSortAlgorithm.*;

public class Run {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Айсберг\\algorithms_201_1" +
                "\\src\\main\\java\\ru\\itis\\algorithms_201_1\\gimaletdinova\\data.txt");

        FileWriter fileWriter = new FileWriter(file);

        for (int i = 1; i < 101; i++) {
            String path = "C:\\Users\\Айсберг\\algorithms_201_1" +
                    "\\src\\main\\java\\ru\\itis\\algorithms_201_1\\gimaletdinova\\files\\file";
            path += String.valueOf(i);
            path += ".txt";

            int[] data = smoothSort(read(path));

            fileWriter.write(data.length + " " + getTime() + " " + getCount() + "\n");
        }
        fileWriter.close();


    }

    private static int[] read(String fileName) throws IOException{
            return Files.lines(Paths.get(fileName))
                    .mapToInt(s -> Integer.parseInt(s.trim()))
                    .toArray();
    }
}
