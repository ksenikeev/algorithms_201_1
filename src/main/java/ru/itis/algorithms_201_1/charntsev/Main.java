package charntsev;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<GrahamAlgorithm.Point> list = new ArrayList<>();
        list.add(0,new GrahamAlgorithm.Point(1,2));
        list.add(1,new GrahamAlgorithm.Point(3,2));
        list.add(2,new GrahamAlgorithm.Point(1,5));
        list.add(3,new GrahamAlgorithm.Point(5,1));
        list.add(4,new GrahamAlgorithm.Point(0,0));
        list.add(5,new GrahamAlgorithm.Point(7,5));
        list.add(6,new GrahamAlgorithm.Point(4,9));
        list.add(7,new GrahamAlgorithm.Point(5,4));
        list.add(7,new GrahamAlgorithm.Point(7,2));

        long start = System.nanoTime();
        GrahamAlgorithm gr = new GrahamAlgorithm(list);
        gr.buildHull();
        gr.getHull();
        long finish = System.nanoTime();

        System.out.println((double) (finish - start) / 1.E+9);
    }
}