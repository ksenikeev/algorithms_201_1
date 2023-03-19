package ru.itis.algorithms_201_1.melnikova;

public class CombSortAlgorithm {

    public static <T extends Comparable<? super T>> void combSort(T[] array){
        final double FACTOR = 1.2473;
        int gap  = array.length - 1;

        while (gap > 1){
            gap /= FACTOR;

            for (int i = gap; i < array.length; i++){
                if (array[i - gap].compareTo(array[i]) > 0){
                    T tmp = array[i];
                    array[i] = array[i - gap];
                    array[i - gap] = tmp;
                }
            }
        }
    }
}
