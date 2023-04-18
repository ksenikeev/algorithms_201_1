import java.util.Arrays;

public class Main {
    public static int[] a;
    public static void main(String[] args) {
        a = new int[]{1, 5, 8, 4, 3, 5, 5, 1};
        qSort(0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void qSort(int l, int r) {
        int pivotInd = (l + r) / 2;
        int pivot = a[pivotInd];
        int i = l;
        int j = r;
        while (i <= j) {
            while (a[i] < pivot) i++;
            while (a[j] > pivot) j--;
            System.out.println(Arrays.toString(a));
            System.out.println(i + " " + j);
            if (i <= j) {
                System.out.println("swapped");
                swap(i, j);
                i++;
                j--;
            }
            System.out.println(Arrays.toString(a));
        }
        if (l < j) {
            qSort(l, j);
        }
        if (r > i) {
            qSort(i, r);
        }

    }

    public static void swap(int i, int j) {
        int x = a[j];
        a[j] = a[i];
        a[i] = x;
    }
}