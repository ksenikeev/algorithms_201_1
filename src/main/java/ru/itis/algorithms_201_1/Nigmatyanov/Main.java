package ru.itis.algorithms_201_1.Nigmatyanov;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // создание 1000 файлов и заполнение их числами от 500 до 1500 в количестве от 0 до 10000.
        for (int i = 0 ; i < 1000; i++){
            String filePath = "src/main/java/ru/itis/algorithms_201_1/Nigmatyanov/tests/" + i + ".txt";
            OutputStreamWriter outputStreamWriter = new FileWriter(filePath);
            int randomLength = 10000 * (i % 10) + (int)(Math.random() * 10000);
            for (int q = 0; q < randomLength; q++) {
                int randomNumber = 500 + (int)(Math.random() * 1000);
                outputStreamWriter.write(String.valueOf(randomNumber) + "\n");
            }
            outputStreamWriter.close();
        }
        String fileName = "src/main/java/ru/itis/algorithms_201_1/Nigmatyanov/output.txt";
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            File file = new File("src/main/java/ru/itis/algorithms_201_1/Nigmatyanov/tests/" + i + ".txt");
            Scanner sc = new Scanner(file);
            ArrayList<String> ar = new ArrayList<>();

            while (sc.hasNext()) {
                ar.add(sc.next());
            }
            int[] arr = new int[ar.size()];
            for (int q = 0; q < ar.size(); q++) {
                arr[q] = Integer.parseInt(ar.get(q));
            }


            long minTime = Integer.MAX_VALUE;
            int minInterations = Integer.MAX_VALUE;
            TimSortV2 timSort;
            for (int q = 0; q < 3; q++){
                int[] tempArr = Arrays.copyOf(arr,arr.length);
                timSort = new TimSortV2();
                long start = System.nanoTime();
                timSort.timSort(tempArr);
                long end = System.nanoTime();
                if (end - start < minTime){
                    minTime = end - start;
                    minInterations = timSort.iterations;
                }
            }

            lines.add(minInterations + " " + arr.length + " " + minTime);
        }
        Files.write(Paths.get(fileName), lines);
    }
}