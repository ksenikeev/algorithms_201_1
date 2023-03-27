package derezhenko;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Randomizer {
    public static void main(String[] args) {
        File out = new File("random.txt");
        FileWriter fw = null;

        try {
            fw = new FileWriter(out);
            BufferedWriter writer = new BufferedWriter(fw);
            int line;
            int line1;
            Random random = new Random();
//            for(int i = 0; i < 50; i++) {
                int n = random.nextInt(100, 10000);
                while (n > 0) {
                    line = random.nextInt(100);
                    line1 = random.nextInt(100);
                    writer.write(line + " " + line1 + "\r\n");
                    n--;
//                }
//                writer.write("*" + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}

