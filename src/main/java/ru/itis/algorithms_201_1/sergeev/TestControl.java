package ru.itis.algorithms_201_1.sergeev;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class TestControl {
    private final static Random random = new Random();

    public static void createTests() {
        for (int j = 0; j < 100; j++) {
            try {
                int n = random.nextInt(100, 10000);
                BufferedWriter writer = new BufferedWriter(new FileWriter("Tests/Test" + (j + 1) + ".txt"));
                for (int i = 0; i < n; i++) {
                    writer.write(String.valueOf(random.nextInt(0, 50000)));
                    writer.write("\n");
                }
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void writeOutput() throws IOException {
        BufferedWriter fileMainOutput = new BufferedWriter(new FileWriter("Outputs/MainOutput.txt"));
        for (int i = 1; i <= 100; i++) {
            try {
                BufferedReader fileInput = new BufferedReader(new FileReader("Tests/Test"+i+".txt"));
                BufferedWriter fileOutput = new BufferedWriter(new FileWriter("Outputs/Outputs"+i+".txt"));
                long t1 = System.nanoTime();
                Tree tree = new Tree(Integer.parseInt(fileInput.readLine()));
                int operations = 1;
                while (true) {
                    try {
                        operations += tree.insert(new Tree(Integer.parseInt(fileInput.readLine())));
                    }
                    catch (Exception e) {
                        break;
                    }
                }
                long t2 = System.nanoTime();
                ArrayList<Integer> integers = new ArrayList<>();
                tree.traverse(integers);
                for (Integer integer : integers) {
                    fileOutput.write(String.valueOf(integer));
                    fileOutput.write("\n");
                }

                fileMainOutput.write("Test " + i + ": " + integers.size() + " " + operations + " " + (t2 - t1) + "\n");
                fileInput.close();
                fileOutput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fileMainOutput.close();
    }
}
