package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class HeapSort {
    public static void sort(int arr[]) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    static void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    static void printArray(long arr[][]) {
        int n = arr.length;
        for (long[] j : arr) System.out.print(j[0] + " "+j[1]+"\n");
        System.out.println();
    }

    public static void main(String args[]) {
        long[][] dataTime=new long[100][2];
        int t=0;
        int[][] dataIterations=new int[100][2];
        int iter=0;
        int iterations=0;
        for (int i = 1; i <= 100; i++) {
            try (Scanner sc = new Scanner(new FileReader("C:\\Users\\leing\\IdeaProjects\\HeapSort\\tests\\" + i + ".txt"))) {
                int lenght = sc.nextInt();
                dataTime[t][0]=lenght;
                dataIterations[iter][0]=lenght;
                int[] arr = new int[lenght];
                int j = 0;
                while (sc.hasNext()) {
                    arr[j] = sc.nextInt();
                    j++;
                }
                long time = System.nanoTime();
                int r=0;
                System.out.println(System.nanoTime()-time);
                sort(arr);
                time=System.nanoTime() - time;
//                dataTime[t][1]= (int) TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS);
                dataTime[t][1]= time;
                t++;
                dataTime[iter][1]=iterations;
                iterations=0;
                iter++;

            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
            printArray(dataTime);
        }
    }
}