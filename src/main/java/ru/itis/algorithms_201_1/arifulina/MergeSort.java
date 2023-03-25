package ru.itis.algorithms_201_1.arifulina;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static long iterations;
    public static <T extends Comparable<? super T>> List<T> merge(List<T> a, List<T> b, List<T> c){
        int i = 0, j = 0, k = 0;
        int n = a.size();
        int m = b.size();
        while (i < n && j < m){
            if (a.get(i).compareTo(b.get(j)) < 0){
                c.set(k, a.get(i)); i++;
            } else {
                c.set(k, b.get(j)); j++;
            }
            k++;
            iterations++;
        }
        while (i < n) {
            c.set(k, a.get(i)); i++; k++; iterations++;}
        while (j < m) {
            c.set(k, b.get(j)); j++; k++; iterations++;}
        return c;
    }

    public static <T extends Comparable<? super T>> List<T> mergeSort(List<T> arr){
        if (arr.size() > 1){
            int half = arr.size() % 2 == 0 ? arr.size() / 2 : arr.size() / 2 + 1;
            List<T> firstHalf = new ArrayList<>(arr.subList(0, half));
            List<T> secondHalf = new ArrayList<>(arr.subList(half, arr.size()));
            return merge(mergeSort(firstHalf), mergeSort(secondHalf), arr);
        } else {
            return arr;
        }
    }
}
