package ru.itis.algorithms_201_1.Kuzmin;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

//кол-во элементов; количество вершин; кол-во рёбер; s; v1,v2: вес;...
public class Generator {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("testData.txt", true);
        for (int i = 0; i <= 100; i++) {
            HashSet<ArrayList<Integer>> arcs = new HashSet<>();
            int n = (int) (Math.random() * ((100 - 10) + 1)) + 10;
            int s = (int) (Math.random() * (n));
            int maxM = n*(n-1);
            int m = (int) (Math.random() * ((maxM - 10) + 1)) + 10;
            StringBuilder str = new StringBuilder(Integer.toString(n+m) + ";" + n + ";" + m + ";" + s + ";");
            for (int j = 0; j < m; j++) {
                while (true) {
                    int v1 = (int) (Math.random() * (n));
                    int v2 = (int) (Math.random() * (n));
                    ArrayList<Integer> a = new ArrayList<>(); a.add(v1); a.add(v2);
                    if (v1 != v2 && !arcs.contains(a)) {
                        arcs.add(a);
                        str.append(v1 + "," + v2 + ":" + (int)(Math.random() * (100 + 1)) + ";");
                        break;
                    }
                }
            }
            writer.append(str.toString() + "\n");
        }
        writer.flush();
    }
}
