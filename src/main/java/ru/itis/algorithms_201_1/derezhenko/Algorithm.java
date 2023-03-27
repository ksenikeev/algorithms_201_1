package derezhenko;

import java.util.*;

public class Algorithm {

    public static ArrayList<Point> convexHull(Point[] points) {
        int cnt = 0;
        ArrayList<Point> hull = new ArrayList<Point>();

        int leftmost = 0;
        for (int i = 1; i < points.length; i++) {
            cnt++;
            if (points[i].x < points[leftmost].x)
                leftmost = i;
        }

        int pointOnHull = leftmost;
        do {
            hull.add(points[pointOnHull]);
            int endpoint = 0;
            for (int i = 1; i < points.length; i++) {
                cnt++;
                if (endpoint == pointOnHull) {
                    endpoint = i;
                    continue;
                }
                Vector v1 = new Vector(points[endpoint].x - hull.get(hull.size()-1).x, points[endpoint].y - hull.get(hull.size()-1).y);
                Vector v2 = new Vector(points[i].x - hull.get(hull.size()-1).x, points[i].y - hull.get(hull.size()-1).y);
                double crossProduct = Vector.crossProduct(v1, v2);
                if (crossProduct < 0)
                    endpoint = i;
            }
            pointOnHull = endpoint;
        } while (pointOnHull != leftmost);
        System.out.println("iterations = " + cnt);
        return hull;
    }
}

class Vector {
    public int x, y;
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static double crossProduct(Vector v1, Vector v2) {
        return v1.x * v2.y - v1.y * v2.x;
    }
}