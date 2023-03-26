package ru.itis.algorithms_201_1.Nigmatyanov;

import java.util.*;

public class TimSortV2 {

    public static int MAX_MERGE_LENGTH = 64;


    public int minRunLength(int n) {
        int r = 0;
        while (n >= MAX_MERGE_LENGTH) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    public void insertionSort(int[] arr, int left,
                              int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public void reversePartOfArray(int[] arr, int left, int right) {
        int iteration = 0;
        while (left < right - iteration) {
            int temp = arr[left];
            arr[left] = arr[right - iteration];
            arr[right - iteration] = temp;
            left++;
            iteration++;
        }
    }

    public int makeNewRun(int[] arr, int index) {
        int minRun = minRunLength(arr.length);
        int arrLen = arr.length;
        int rightCursor = index;

        if (arrLen - index - 2 <= minRun) {
            insertionSort(arr, index, arrLen - 1);
            return arrLen;
        }

        if (arr[rightCursor] < arr[rightCursor + 1]) {
            while (rightCursor < arrLen - 1 && arr[rightCursor] <= arr[rightCursor + 1]) {
                rightCursor++;
            }
        } else {
            while (rightCursor < arrLen - 1 && arr[rightCursor] > arr[rightCursor + 1]) {
                rightCursor++;
            }
            reversePartOfArray(arr, index, rightCursor);
        }
        if (arrLen - index - 2 <= minRun) {
            rightCursor = arrLen - 1;
        } else if (rightCursor - index + 1 < minRun) {
            rightCursor += minRun - (rightCursor - index) - 1;
        }
        insertionSort(arr, index, rightCursor);
        rightCursor++;
        return rightCursor;
    }
    public void mergeTwoSubarrays(int[] arr, int firstSubarrayIndex, int firstSubarrayLength, int secondSubarrayIndex, int secondSubarrayLength) {
        int[] tempArray = Arrays.copyOfRange(arr, firstSubarrayIndex, firstSubarrayIndex + firstSubarrayLength);
        int cursor1 = 0;
        int cursor2 = 0;
        int minIndex = Math.min(firstSubarrayIndex, secondSubarrayIndex);
        while (cursor1 <= tempArray.length - 1 && cursor2 <= secondSubarrayLength - 1) {
            if (tempArray[cursor1] <= arr[secondSubarrayIndex + cursor2]) {
                arr[minIndex + cursor1 + cursor2] = tempArray[cursor1];
                cursor1++;
            } else {
                arr[minIndex + cursor1 + cursor2] = arr[secondSubarrayIndex + cursor2];
                cursor2++;
            }
        }
        if (cursor1 == firstSubarrayLength) {
            while (cursor2 < secondSubarrayLength) {
                arr[minIndex + cursor1 + cursor2] = arr[secondSubarrayIndex + cursor2];
                cursor2++;
            }
        } else {
            while (cursor1 < firstSubarrayLength) {
                arr[minIndex + cursor1 + cursor2] = tempArray[cursor1];
                cursor1++;
            }
        }
    }
    public void timSort(int[] arr) {
        ArrayList<int[]> runs = new ArrayList<>();
        int arrLen = arr.length;
        int cursor = 0;
        int newRunBeginning = makeNewRun(arr, cursor);
        runs.add(new int[]{cursor, newRunBeginning - cursor});
        while (newRunBeginning != arrLen) {
            cursor = newRunBeginning;
            newRunBeginning = makeNewRun(arr, cursor);
            runs.add(new int[]{cursor, newRunBeginning - cursor});
            while (runs.size() != 1 && runs.get(runs.size() - 1)[1] >= runs.get(runs.size() - 2)[1]
                            || ( runs.size() >= 3 && runs.get(runs.size() - 3)[1] <= runs.get(runs.size() - 1)[1] + runs.get(runs.size() - 2)[1])) {
                if (runs.size() == 2){
                    mergeTwoSubarrays(arr, runs.get(0)[0], runs.get(0)[1], runs.get(1)[0], runs.get(1)[1]);
                    runs.get(0)[1] += runs.get(1)[1];
                    runs.remove(1);
                }
                else{
                    int minIndex;
                    int minLen;
                    int runsSize = runs.size();
                    boolean isZLenLessThanX = runs.get(runsSize - 3)[1] < runs.get(runsSize - 1)[1];
                    if (isZLenLessThanX) {
                        minIndex = runs.get(runsSize - 3)[0];
                        minLen = runs.get(runsSize - 3)[1];
                    } else {
                        minIndex = runs.get(runsSize - 1)[0];
                        minLen = runs.get(runsSize - 1)[1];
                    }
                    if (minIndex < runs.get(runsSize - 2)[0]) {
                        mergeTwoSubarrays(arr, minIndex, minLen, runs.get(runsSize - 2)[0], runs.get(runsSize - 2)[1]);
                    } else {
                        mergeTwoSubarrays(arr, runs.get(runsSize - 2)[0], runs.get(runsSize - 2)[1], minIndex, minLen);
                    }
                    if (!isZLenLessThanX) {
                        runs.get(runsSize - 2)[1] = runs.get(runsSize - 2)[1] + runs.get(runsSize - 1)[1];
                        runs.remove(runsSize - 1);
                    } else {
                        runs.get(runsSize - 3)[1] = runs.get(runsSize - 2)[1] + runs.get(runsSize - 3)[1];
                        runs.set(runsSize - 2, runs.get(runsSize - 1));
                        runs.remove(runsSize - 1);
                    }
                }
            }
        }
        for (int i = runs.size() - 1; i > 0; i--){
                mergeTwoSubarrays(arr, runs.get(i-1)[0], runs.get(i-1)[1], runs.get(i)[0], runs.get(i)[1]);
                runs.get(i-1)[1] += runs.get(i)[1];
                runs.remove(i);
            }
    }
}
