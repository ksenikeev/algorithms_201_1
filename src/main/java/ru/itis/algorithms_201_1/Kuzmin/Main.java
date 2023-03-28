package ru.itis.algorithms_201_1.Kuzmin;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("outputData.txt", true);
        ArrayList<String> arr = (ArrayList<String>) Files.lines(Paths.get("testData.txt")).collect(Collectors.toList());

        for (String str : arr) {
            String[] strArr = str.split(";");
            int cntElem = Integer.parseInt(strArr[0]);
            int n = Integer.parseInt(strArr[1]);
            int m = Integer.parseInt(strArr[2]);
            int s = Integer.parseInt(strArr[3]);
            int[][] adjacencyList = new int[n][];
            for (int i = 0; i < adjacencyList.length; i++ ) adjacencyList[i] = new int[0];
            HashMap<ArrayList<Integer>, Double> arcWeight = new HashMap<>();
            String[] arcs = Arrays.copyOfRange(strArr, 4, strArr.length);
            for (String arc: arcs) {
                String[] a = arc.split(",|:");
                int v1 = Integer.parseInt(a[0]);
                int v2 = Integer.parseInt(a[1]);
                double weight = Double.parseDouble(a[2]);
                ArrayList<Integer> arrayList = new ArrayList<>(); arrayList.add(v1); arrayList.add(v2);
                arcWeight.put(arrayList, weight);
                adjacencyList[v1] = Arrays.copyOf(adjacencyList[v1], adjacencyList[v1].length+1);
                adjacencyList[v1][adjacencyList[v1].length-1] = v2;

            }
            long start = System.nanoTime();
            algLevita(adjacencyList, arcWeight, s);
            long finish = System.nanoTime();
            long elapsed = finish - start;

            writer.append(strArr[1].toString() + ";" + strArr[2].toString() + ";" + elapsed + ";" + cnt + "\n");
            cnt = 0;
        }
        writer.flush();
    }

    public static double[] algLevita(int[][] adjacencyList, HashMap<ArrayList<Integer>, Double> arcWeight, int s) {
        double[] d = new double[adjacencyList.length];
        // O(n)
        for (int i = 0; i < adjacencyList.length; i++) {
            cnt += 1;
            d[i] = Double.POSITIVE_INFINITY;
        }
        d[s] = 0;
        HashSet<Integer> M0 = new HashSet<>();
        HashSet<Integer> M2 = new HashSet<>();
        HashSet<Integer> M1 = new HashSet<>();
        PriorityQueue<Integer> M11 = new PriorityQueue<>();
        PriorityQueue<Integer> M12 = new PriorityQueue<>();

        M11.add(s);
        M1.add(s);

        // O(n)
        for (int i = 0; i < adjacencyList.length; i++) {
            if (i != s){
                M2.add(i);
                cnt += 1;
            }
        }
        // O(nm)
        while (M1.size() != 0) {
            int u;
            if (M12.size() != 0) {
                u = M12.poll();
                M1.remove(u);
            } else {
                u = M11.poll();
                M1.remove(u);
            }
            // O(n)
            for (int v: adjacencyList[u]) {
                ArrayList<Integer> a = new ArrayList<>(); a.add(u); a.add(v);
                if (M2.contains(v)) {
                    M11.add(v);
                    M1.add(v);
                    M2.remove(v);
                    d[v] = d[u] + arcWeight.get(a);
                    cnt += 1;
                }
                else if (M1.contains(v)) {
                    d[v] = Math.min(d[v], d[u] + arcWeight.get(a));
                    cnt += 1;
                }
                else if(M0.contains(v) && d[v] > (d[u] + arcWeight.get(a))) {
                    M12.add(v);
                    M1.add(v);
                    M0.remove(v);
                    d[v] = d[u] + arcWeight.get(a);
                    cnt += 1;
                }
            }
            M0.add(u);
            cnt += 1;
        }
        return d;
    }
}

