package ru.itis.algorithms_201_1.bagaviev;

import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {

    private static int bucketSort(int[] array, int bucketsCount) {

        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;

        int iterationsCount = 2;

        for (int num : array) {
            maxValue = Math.max(maxValue, num);
            minValue = Math.min(minValue, num);

            iterationsCount+= 2;

        }

        //Initializing buckets
        LinkedList<Integer>[] buckets = new LinkedList[bucketsCount];
        for (int i = 0; i < bucketsCount; i++) {
            buckets[i] = new LinkedList<>();
            iterationsCount++;
        }

        //Defining the range of buckets values
        int range = maxValue - minValue;
        //Defining the range of each bucket
        int bucketRange = range / bucketsCount + 1;

        iterationsCount += 2;

        //Pushing elements to buckets
        for (int j : array) {

            int index = (j - minValue) / bucketRange;

            buckets[index].add(j);

            iterationsCount += 2;

        }

        //Sorting buckets
        //Bucket sort uses the quick sort to sort each bucket
        for (int i = 0; i < bucketsCount; i++) {
            Collections.sort(buckets[i]);

            int bucketSize = buckets[i].size();

            iterationsCount+= bucketSize * (Math.log(bucketSize) / Math.log(2));
        }


        //Getting elements from buckets to specified array
        int cursor = 0;

        for (int i = 0; i < bucketsCount; i++) {
            for (int k = 0; k < buckets[i].size(); k++) {

                array[cursor++] = buckets[i].get(k);

                iterationsCount++;

            }
        }

        return iterationsCount;

    }

    public static int bucketSort(int[] array) {
        if (array == null || array.length <= 1) return 0;

        return bucketSort(array, array.length);
    }


}
