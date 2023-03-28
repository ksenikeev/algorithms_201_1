package ru.itis.algorithms_201_1.safin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class TestAlgorithm {

    public static void createTest() {

        for (int i = 0; i < 100; i++) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Test/test" + (i+1) + ".txt"));
                int n = 100 + (int) ( Math.random() * 10000 );
                writer.write(Integer.toString(n));
                writer.write('\n');
                for (int j = 0; j < n; j++) {
                    int a = (int) ( Math.random() * 10000 );
                    writer.write(Integer.toString(a));
                    writer.write('\n');
                }
                writer.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeStatistics() {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("Output/output.txt"));
            for (int i = 1; i <= 100; i++) {
                BufferedReader reader = new BufferedReader(new FileReader("Test/test"+i+".txt"));
                int n = Integer.parseInt(reader.readLine());
                output.write(Integer.toString(n) + " ");
                int[] mas = new int[n];
                int[] arr = new int[n];
                for (int j = 0; j < n; j++) {
                    int a = Integer.parseInt(reader.readLine());
                    mas[j] = a;
                    arr[j] = a;
                }
                long t1 = System.nanoTime();
                int iterations = MergeSort.sort(mas, mas.length);
                long t2 = System.nanoTime();
                Arrays.sort(arr);
                boolean flag = Arrays.equals(arr, mas);
                output.write(Integer.toString(iterations) + " ");
                output.write(Long.toString(t2 - t1) + " ");
                output.write(Boolean.toString(flag));
                output.write("\n");
            }
            output.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
