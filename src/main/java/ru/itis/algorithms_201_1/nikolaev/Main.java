package ru.itis.algorithms_201_1.nikolaev;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) throws Exception {
        int countOfSets = 100;
        int max = 5000;
        int min = 100;
        int abs = 10000;
        Path path = Paths.get("src\\main\\java\\ru\\itis\\algorithms_201_1\\nikolaev\\data.txt");
        FileWriter writer = new FileWriter(path.toString());
        for (int i = 0; i < countOfSets; i++) {
            int x = (int) (Math.random() * ((max - min) + 1)) + min;
            int[] a = (new RandomNumberIterator(x, abs)).data;
            long start = System.currentTimeMillis();
            StoogeSort.sort(a);
            long finish = System.currentTimeMillis();
            long duration = finish - start;
            int num1 = x;
            writer.write("" + num1);
            writer.append(' ');
            long num2 = StoogeSort.count;
            writer.write("" + num2);
            writer.append(' ');
            long num3 = duration;
            writer.write("" + (int) num3);
            writer.append('\n');
            writer.flush();
            StoogeSort.count = 0;
        }
    }
}
