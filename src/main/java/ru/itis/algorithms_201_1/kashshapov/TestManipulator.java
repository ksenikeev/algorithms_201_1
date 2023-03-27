package ru.itis.algorithms_201_1.kashshapov;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class TestManipulator {

    private final static Random ran = new Random();

    public static void createTests() {
        int n = 0;
        for (int j = 0; j < 100; j++) {
            try {
                StringBuilder s = new StringBuilder();
                StringBuilder s1 = new StringBuilder();
                BufferedWriter writer = new BufferedWriter(new FileWriter("Tests/Test" + (j + 1) + ".txt"));
                n += 100;
                int k = ran.nextInt(8) + 1;
                for (int i = 0; i < n; i++) {
                    s.append((char) (ran.nextInt(26) + 97));
                }
                for (int i = 0; i < k; i++) {
                    s1.append((char) (ran.nextInt(26) + 97));
                }
                writer.write(s.toString());
                writer.write('\n');
                writer.write(s1.toString());
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeOutput(){
        for (int i = 1; i <= 100; i++) {
            try {
                BufferedReader r = new BufferedReader(new FileReader("Tests/Test"+i+".txt"));
                BufferedWriter output = new BufferedWriter(new FileWriter("Outputs/Output"+i+".txt"));
                String s = r.readLine();
                String sub = r.readLine();
                long t1 = System.nanoTime();
                ArrayList<Integer> e = BoyerMooreAlgorithm.algorithm(s, sub);
                long t2 = System.nanoTime();
                output.write("Test " + i + " " + e.toString() + "\n");
                output.write(s.length() + " ");
                output.write(Double.toString(((double) t2 - t1) / 1_000_000) + " ");
                output.write(Integer.toString(BoyerMooreAlgorithm.iterations));
                r.close();
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void printOutput(){
        for (int i = 1; i <= 100; i++){
            try{
                BufferedReader r = new BufferedReader(new FileReader("Outputs/Output"+i+".txt"));
                r.readLine();
                System.out.println(r.readLine());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}

