package ru.itis.algorithms_201_1.gimaletdinova;


import java.util.*;

public class SmoothSortAlgorithm {
//  зависимость от количества элементов
    private static int count; // количество операций
    private static long time; // затраченное время
    private static List<Integer> heap; // хранит порядки всех деревьев
    private static List<Integer> leonardoNumbers; // хранит количество чисел Леонардо
                                                 // (не превышает длину заданного массива)
    private static int rightChild; // индекс правого ребенка
    private static int sizeOfRightChild;
    private static int leftChild; // индекс левого ребенка
    private static int sizeOfLeftChild;

    public static int[] smoothSort(int[] array) {
        long startTime = System.nanoTime();
        count = 0;
        getLeoNums(array.length);
        sort(heapify(array));
        time = System.nanoTime() - startTime;
        return array;
    }

//    приводит данные к виду леонардовых деревьев
    private static int[] heapify(int[] array) {
        heap = new ArrayList<>();
        heap.add(1);
        ++count;
        for (int i = 1; i < array.length; i++) {
            ++count;
            int lastIndexOfHeap = heap.size() - 1;
            int ultimateElem = heap.get(lastIndexOfHeap);

            if (heap.size() >= 2) {
                int penultimateElem = heap.get(lastIndexOfHeap - 1);
                if (penultimateElem == ultimateElem + 1) {
                    heap.remove(lastIndexOfHeap);
                    heap.set(lastIndexOfHeap - 1, penultimateElem + 1);
                    restoreHeap(array, i);
                    continue;
                }
            }
            if (ultimateElem == 1) {
                heap.add(0);
            }
            else {
                heap.add(1);
            }
            restoreHeap(array, i);
        }
        return array;
    }

    // восстанавливает кучи после слияния/удаления
    private static void restoreHeap(int[] array, int currentIndex) {
        int lastIndexOfHeap = heap.size() - 1;
        int currentHeapSize = heap.get(lastIndexOfHeap);

//      сравниваем корни деревьев (должны быть отсортированы по возрастанию)
        while (lastIndexOfHeap > 0) {
            int indexOfComparingHeap = currentIndex - leonardoNumbers.get(currentHeapSize);
            if ((array[indexOfComparingHeap] > array[currentIndex]) && (currentHeapSize < 2 ||
                                                    array[indexOfComparingHeap] > array[currentIndex - 1]
                                                            && array[indexOfComparingHeap] > array[currentIndex - 2])) {
                int temp = array[currentIndex];
                array[currentIndex] = array[indexOfComparingHeap];
                array[indexOfComparingHeap] = temp;
                currentIndex = indexOfComparingHeap;
                --lastIndexOfHeap;
                currentHeapSize = heap.get(lastIndexOfHeap);
                ++count;
            }
            else {
                break;
            }
        }

        // балансируем детей и родителей
        while (currentHeapSize >= 2) {
            getChildTrees(currentIndex, currentHeapSize);

            if (array[currentIndex] < array[rightChild] || array[currentIndex] < array[leftChild]) {
                if (array[rightChild] > array[leftChild]) {
                    int temp = array[rightChild];
                    array[rightChild] = array[currentIndex];
                    array[currentIndex] = temp;

                    currentIndex = rightChild;
                    currentHeapSize = sizeOfRightChild;
                }
                else {
                    int temp = array[leftChild];
                    array[leftChild] = array[currentIndex];
                    array[currentIndex] = temp;

                    currentIndex = leftChild;
                    currentHeapSize = sizeOfLeftChild;
                }
                ++count;
            }
            else {
                break;
            }
        }
    }

    private static void sort(int[] array) {
        for (int i = array.length - 1 ; i >= 0; i--) {

            ++count;

            int lastIndexOfHeap = heap.size() - 1;
            int currentHeapSize = heap.remove(lastIndexOfHeap);
            if (currentHeapSize >= 2) {

                getChildTrees(i, currentHeapSize);
                int tempSizeOfRightChild = sizeOfRightChild;
                int tempRightChild = rightChild;

                heap.add(sizeOfLeftChild);
                restoreHeap(array, leftChild);

                heap.add(tempSizeOfRightChild);
                restoreHeap(array, tempRightChild);
                ++count;

            }
        }
    }

    private static void getChildTrees(int currentIndex, int size) {
        ++count;
        rightChild = currentIndex - 1;
        sizeOfRightChild = size - 2;
        leftChild = rightChild - leonardoNumbers.get(sizeOfRightChild);
        sizeOfLeftChild = size - 1;
    }

    private static void getLeoNums(int len) {
        leonardoNumbers = new ArrayList<>();
        int a = 1;
        int b = 1;
        while (len >= a) {
            ++count;
            leonardoNumbers.add(a);
            int temp = b;
            b += a + 1;
            a = temp;
        }
    }

    public static int getCount() {
        return count;
    }

    public static long getTime() {
        return time;
    }

}