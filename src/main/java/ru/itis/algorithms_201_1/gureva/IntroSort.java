package ru.itis.algorithms_201_1.gureva;

import java.util.Arrays;
import static java.lang.Math.*;

public class IntroSort {
    private static int constForInsertionSort = 16;

    public static void sort(int[] arr) {
        int limit = 2 * (int) (Math.log(arr.length)/ Math.log(2));
        introSort(arr, limit, 0, arr.length - 1);
    }
    public static void introSort(int[] arr, int limit, int start, int end) {
        if (end - start == 0) return;
        int n = end - start + 1;
        if (n <= constForInsertionSort) {
            insertionSort(arr, start, end);
        }
        else if (limit == 0) {
            heapSort(arr, start, end);
        }
        else quickSort(arr, limit, start, end);
    }

    private static void insertionSort(int[] arr, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            for (int j = i; j > start; j--) {
                if (arr[j - 1] > arr[j]) swap(arr, j - 1, j);
            }
        }
    }
    private static void heapSort(int[] arr, int start, int end) {
        int[] array = new int[end - start + 1];
        doThree(arr, start, end);
        doArray(array, arr, start, end);
        int j = 0;
        for (int i = start; i <= end; i++) {
            arr[i] = array[j];
            j++;
        }
    }

    private static void doArray(int[] array, int[] arr, int start, int end) {
        for (int i = 0; i < array.length; i++) {
            array[i] = arr[start];
            start++;
            doThree(arr, start, end);
        }
    }

    private static void doThree(int[] array, int start, int end) {
        int[] arr = Arrays.copyOfRange(array, start, end + 1);
        int index;
        for (int i = 0; i < arr.length; i++) {
            index = i;
            while (index != 0 && arr[index] <= arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
        int j = 0;
        for (int i = start; i <= end; i++) {
            array[i] = arr[j];
            j++;
        }
    }

    private static int findPivot(int[] arr, int start, int end) {
        int first = arr[start];
        int second = arr[(end - start) / 2];
        int third = arr[end];
        int pivot = first + second + third - max(max(first, second), third) - min(min(first, second), third);
        return pivot;
    }

    private static void quickSort(int[] arr, int limit, int start, int end) {
        limit--;
        // pivot - опорный элемент массива
        int pivot = findPivot(arr, start, end);
        int i = start, j = end;

        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        introSort(arr, limit, start, j);
        introSort(arr, limit, i, end);
    }

    private static void swap(int[] arr, int i, int j) {
        int ans = arr[i];
        arr[i] = arr[j];
        arr[j] = ans;
    }
}

