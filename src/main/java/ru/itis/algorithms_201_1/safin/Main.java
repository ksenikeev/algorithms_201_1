package ru.itis.algorithms_201_1.safin;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            int a = (int) ( Math.random() * 30000 );
            arr[i] = a;
        }
        System.out.println(MergeSort.sort(arr, arr.length));
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}