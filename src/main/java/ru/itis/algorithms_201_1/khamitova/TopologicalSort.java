package ru.itis.algorithms_201_1.khamitova;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TopologicalSort {
    public static Map<Integer, Boolean> visited = new HashMap<>(); //посещенные вершины
    public static Deque<Integer> order = new ArrayDeque<>(); //итоговый порядок вершин
    public static int count; //количество операций
    public static long executTime; //время выполнения
    public static int n; //количество вершин
    public static int e; //количество ребер

    public static void main(String[] args){
        output();
    }

    public static void output(){ //вывод для каждого из 51 тестов
        for (int i = 1; i < 51; i++) {
            String path = "C:/IdeaProjects/algorithms_201_1/" + i + ".txt";
            String s = "C:/IdeaProjects/algorithms_201_1/results/" + i + ".txt";
            try{
                count = 0;
                File f = new File(s);
                f.createNewFile();
                FileWriter fw = new FileWriter(f, true);
                List<List<Integer>> arr = read(path);
                long beginTime = System.nanoTime();
                for (int j = 0; j < arr.size(); j++) {
                    visited.put(j, false);
                    count++;
                }
                int[] white = new int[arr.size()];
                try {
                    for (int j = 0; j < n; j++) {
                        count++;
                        executTime = System.nanoTime() - beginTime;
                        if (white[j] != 2) checkCycle(j, arr, white);
                    }
                    for (int j = 0; j < n; j++) {
                        count++;
                        if (!visited.get(j)) sort(j, arr);
                    }
                    executTime = System.nanoTime() - beginTime;
                    fw.write (n + " nodes" + "\n");
                    fw.write(e + " edges" + "\n");
                    fw.write(executTime + " nanosec" + "\n");
                    fw.write(count + " iterations" + "\n");
                    for (Integer el : order) {
                        fw.write(el + " ");
                    }
                    fw.close();
                } catch(Exception ex){ //если нашли цикл
                    fw.write (n + " nodes" + "\n");
                    fw.write(e + " edges" + "\n");
                    fw.write(executTime + " nanosec before finding cycle" + "\n");
                    fw.write(count + " iterations before finding cycle");
                    fw.close();
                }
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }


    public static List<List<Integer>> read(String fileName) { //считывание данных
        String str;
        try {
            str = new String(Files.readAllBytes(Paths.get(fileName)));
            String[] s = str.split("\n");
            n = Integer.parseInt(s[0]);
            e = Integer.parseInt(s[1]);
            List<List<Integer>> arr = new ArrayList<>();
            //список списков; arr.get(i) - список, содержащий вершины, в которые есть дуга из вершины i
            for (int i = 0; i < n; i++) {
                arr.add(new ArrayList<Integer>());
            }
            for (int i = 2; i <= e + 1; i++) {
                String[] ss = s[i].split(" ");
                arr.get(Integer.parseInt(ss[0]) - 1).add(Integer.parseInt(ss[1]) - 1);
            }
            return arr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sort(int v, List<List<Integer>> arr){
        visited.replace(v, true);
        for (int i = 0; i < arr.get(v).size(); i++) {
            if (!visited.get(arr.get(v).get(i))) sort(arr.get(v).get(i), arr);
        }
        order.addFirst(v + 1);
        count++;
    }

    //проверка на наличие цикла в графе
    public static void checkCycle(int v, List<List<Integer>> arr, int[] white) throws Exception{
        white[v] = 1;
        for (int i = 0; i < arr.get(v).size(); i++) {
            count++;
            if (white[arr.get(v).get(i)] == 0) checkCycle(arr.get(v).get(i), arr, white);
            if (white[arr.get(v).get(i)] == 1) throw new Exception("В графе есть цикл, сортировка невозможна");
        }
        white[v] = 2;
    }
}
