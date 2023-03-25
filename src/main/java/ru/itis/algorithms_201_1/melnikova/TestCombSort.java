package ru.itis.algorithms_201_1.melnikova;

public class TestCombSort {
    public static void main(String[]args){
        generateTestFiles();
    }
    public static void generateTestFiles() {
        Integer[] a;
        int count;
        int start = 100;
        int end = 10001;

        try {
            FileWriter fileWriter = new FileWriter("test1.txt");

            for (int i = start; i < end; i += 10){
                a = new Integer[i];
                for (int j = 0; j < i; j++){
                    a[j] = (int) (Math.random() * i);
                }
                long t = System.nanoTime();
                count = combSort(a);

                fileWriter.write(i + ";" + count + ";" + (System.nanoTime() - t) + "\n");
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static <T extends Comparable<? super T>> int combSort(T[] array){
        final double FACTOR = 1.2473;
        int gap  = array.length - 1;

        int counter = 0;

        while (gap > 1){
            gap /= FACTOR;

            for (int i = gap; i < array.length; i++){
                if (array[i - gap].compareTo(array[i]) > 0){
                    T tmp = array[i];
                    array[i] = array[i - gap];
                    array[i - gap] = tmp;

                    counter++;
                }
            }
        }
        return counter;
    }
}
