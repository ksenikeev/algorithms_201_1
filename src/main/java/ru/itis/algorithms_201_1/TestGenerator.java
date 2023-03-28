package ASD.testsForRadixSort;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestGenerator {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        for (int i = 1; i < 101; i++) {
            int maxim = i * 100;
            String s = "/Users/miron1/IdeaProjects/ASD/src/main/java/ASD/testsForRadixSort/tests/test";
            s += String.valueOf(i);
            s += ".txt";
            File f = new File(s);
            f.createNewFile();
            FileWriter fw = new FileWriter(f, true);
            for (int j = 0; j < maxim; j++) {
                fw.write(rand.nextInt(10000) + 1 + "\n");
            }
            fw.close();
        }
    }
}
