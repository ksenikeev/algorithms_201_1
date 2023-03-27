package derezhenko;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Point> points = new ArrayList<>();
        BufferedReader reader;
        int countOfLines = 0;
        try {
            reader = new BufferedReader(new FileReader("random.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split(" ");
                points.add(new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])));
                line = reader.readLine();
                countOfLines++;
            }
            reader.close();
            System.out.println(points);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis();
        ArrayList<Point> hullPoints = Algorithm.convexHull(points.toArray(new Point[points.size()]));
        System.out.println("lines = " + countOfLines);
        long timeMillis = System.currentTimeMillis() - time;
        System.out.println("TimeMillis = " + (timeMillis));
    }
}

//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//    public static void main(String[] args) throws IOException {
//        int[][] points = new int[2][7];
//
//        System.out.println();
//
//    }
//
//
//
//
//
//
//    public static List<int[]> readCoords() throws IOException { //сделать двумерный массив, где 1 строчка - иксы, вторая - игрики (т.е 1 столбец - 1 точка)
//        List<String> list = Files.lines(Paths.get("coords"))
//                .toList();
//        List<int[]> points = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            String[] str = list.get(i).split(" ");
//            int x = Integer.parseInt(str[0]);
//            int y = Integer.parseInt(str[1]);
//            int[] coords = {x, y};
//            points.add(coords);
//            System.out.println(points);
//        }
//        return points;
//    }
//}
