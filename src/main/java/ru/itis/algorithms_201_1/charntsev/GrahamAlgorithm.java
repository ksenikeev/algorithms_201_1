package charntsev;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GrahamAlgorithm {
    public static class Point implements Comparator<Point>{
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public int compare(Point o1, Point o2) {
            final double EPS = 1.0E-8;
            if (Math.abs(o1.getY() - o2.getY()) < EPS) {
                if (Math.abs(o1.getX() - o2.getX()) < EPS) return 0;
                else return o1.getX() - o2.getX() < 0  ? -1 : 1;
            }
            return o1.getY() - o2.getY() < 0 ? -1 : 1;
        }
    }
    private List<Point> points;
    private List<Point> hull;
    private int count;

    public GrahamAlgorithm(List<Point> points) {
        this.points = points;
        hull = new ArrayList<>();
    }

    // находит точку с минимальным y и при одинаковом y минимальным x
    private Point findStartPoint() {
        Comparator<Point> comparator = Comparator.comparingDouble(Point::getY).thenComparingDouble(Point::getX);
        return Collections.min(points, comparator);
    }

    // сортирует все точки по углу и расстоянию от начальной точки
    private void sortPointsByAngle() {
        Point startPoint = findStartPoint();
        Comparator<Object> comparator = Comparator.comparingDouble(p -> angle(startPoint, (Point) p))
                .thenComparingDouble(p -> distance(startPoint, (Point) p));
        Collections.sort(points, comparator);
    }

    // возвращает угол между вектором, проведённым из начальной координаты, и осью ОХ
    private double angle(Point start, Point point1) {
        double dx1 = point1.getX() - start.getX();
        double dy1 = point1.getY() - start.getY();
        double angle = Math.atan2(dy1, dx1);
        if (angle < 0.0) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    // возвращает расстояние между точками
    private double distance(Point point1, Point point2) {
        double dx = point1.getX() - point2.getX();
        double dy = point1.getY() - point2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    //даёт возможность получить количество проведённых итераций
    public int getCount() {
        return count;
    }

    // построение выпуклой оболочки, реализация самого метода
    public List<Point> buildHull() {

        sortPointsByAngle();
        hull.add(points.get(0));
        hull.add(points.get(1));

        for (int i = 2; i < points.size(); i++) {
            Point top = hull.get(hull.size() - 1);
            Point nextToTop = hull.get(hull.size() - 2);
            Point point = points.get(i);

            while (position(nextToTop, top, point) <= 0) {
                hull.remove(hull.size() - 1);
                top = hull.get(hull.size() - 1);
                nextToTop = hull.get(hull.size() - 2);
                count++;

            }
            hull.add(point);
            count++;
        }
        return hull;
    }

    // выводит все точки, образующие выпуклую оболочку с помощью алгоритма Грэхема
    public void getHull() {
        for (Point x : hull) {
            System.out.println("(" + x.getX() + ", " + x.getY() + ")");
        }
    }

    // проверяет, с какой стороны от вектора AB находится точка С
    private int position(Point a, Point b, Point c) {
        double area = (b.getX() - a.getX()) * (c.getY() - a.getY()) - (c.getX() - a.getX()) * (b.getY() - a.getY());
        if (area > 0) return 1;
        else if (area < 0) return -1;
        else return 0;

    }
}