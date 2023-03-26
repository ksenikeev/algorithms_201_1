package ru.itis.algorithms_201_1.khamitova;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestGenerator {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 51; i++) {
            String s = "C:/IdeaProjects/algorithms_201_1/" + i + ".txt";
            File f = new File(s);
            f.createNewFile();
            FileWriter fw = new FileWriter(f, true);
            int n = i * 100;
            fw.write(n + "\n");
            int e = (int) Math.round(Math.random()*n + 1);
            //максимум сгенерируется n + 1 ребро - для того чтобы была меньше вероятность возникновения цикла
            fw.write(e + "\n");
            Set<List<Integer>> pairs = new HashSet<>();
            while(pairs.size() < e){
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add((int) Math.round(Math.random() * (n - 1) + 1));
                arr.add((int) Math.round(Math.random() * (n - 1) + 1));
                if (arr.get(0) != arr.get(1)){
                    pairs.add(arr);
                }
            }
            for (List<Integer> el:pairs) {
                fw.write(el.get(0) + " " + el.get(1));
                fw.write("\n");
            }
            fw.close();
        }
    }
}
