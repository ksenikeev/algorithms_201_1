package ASD;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.lang.System;
import static java.lang.System.nanoTime;

public class RadixSort {
    public static int iterCounter;
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 100; i++) {
            File f = new File("/Users/miron1/IdeaProjects/ASD/src/main/java/ASD/testsForRadixSort/tests/test" + i + ".txt");
            FileReader fr = new FileReader(f);
            Scanner sc = new Scanner(fr);
            int size = i * 100;
            int[] arr = new int[size];
            int j = 0;
            while (sc.hasNextLine()) {
                arr[j] = Integer.parseInt(sc.nextLine());
                j++;
            }
            fr.close();
            long start = nanoTime();
            radixSort(arr);
            long time = nanoTime() - start;
            System.out.print(size + " ");
            System.out.print(time + " ");
            System.out.print(iterCounter + " ");
            System.out.println(getMaxNumberOfDigits(arr));
            iterCounter  = 0;
        }
    }

    public static int getDigit(int number, int divider) {
        return (number % (divider * 10)) / divider;
    }

    public static int getMaxNumberOfDigits(int[] numbers) {
        int result = 1;
        for (int number : numbers) {
            int numbOfDigits = 1;
            int multiplier = 10;
            while (number >= multiplier) {
                iterCounter += 1;
                numbOfDigits++;
                multiplier *= 10;
            }
            result = Math.max(result, numbOfDigits);
        }
        return result;
    }

    public static int[] getMaxMinKeys(int[] numbers, int divider) {
        int minKey = getDigit(numbers[0], divider);
        int maxKey = minKey;
        int[] result = new int[2];
        for (int i = 1; i < numbers.length; i++) {
            iterCounter += 1;
            if (getDigit(numbers[i], divider) < minKey) {
                minKey = getDigit(numbers[i], divider);
            }
            if (getDigit(numbers[i], divider) > maxKey) {
                maxKey = getDigit(numbers[i], divider);
            }
        }
        result[0] = minKey;
        result[1] = maxKey;
        return result;
    }

    public static int[] countSort(int[] numbers, int[] minMaxKeys, int divider) {
        int minKey = minMaxKeys[0];
        int maxKey = minMaxKeys[1];
        int rangeOfKeys = maxKey - minKey + 1;
        int[] statistics = new int[rangeOfKeys];
        for (int number : numbers) {
            iterCounter += 1;
            statistics[getDigit(number, divider) - minKey] += 1;
        }
        int size = numbers.length;
        for (int i = rangeOfKeys - 1; i >= 0; i--) {
            iterCounter += 1;
            size -= statistics[i];
            statistics[i] = size;
        }
        int[] drain = new int[numbers.length];
        for (int number : numbers) {
            iterCounter += 1;
            drain[statistics[getDigit(number, divider) - minKey]] = number;
            statistics[getDigit(number, divider) - minKey] += 1;
        }
        return drain;
    }

    public static int[] radixSort(int[] numbers) {
        int divider = 1;
        int maxNumberOfDigits = getMaxNumberOfDigits(numbers);
        for (int i = 0; i < maxNumberOfDigits; i++) {
            numbers = countSort(numbers, getMaxMinKeys(numbers, divider), divider);
            divider *= 10;
        }
        return numbers;
    }
}
