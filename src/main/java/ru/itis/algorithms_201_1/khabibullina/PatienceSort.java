package ru.itis.algorithms_201_1.khabibullina;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;


public class PatienceSort {
    public static void patienceSort(int[] array, int length) {
        List<Pile<Integer>> piles = new ArrayList<>();

        Pile<Integer> st = new Pile<>();
        st.push(array[0]);
        piles.add(st);
        int pileCount = 1;

        for (int i = 1; i < length; i++) {
            int index = binarySearchForPile(array[i], pileCount, piles);

            if (index == pileCount) {
                Pile<Integer> newStack = new Pile<>();
                newStack.push(array[i]);
                piles.add(newStack);
                pileCount++;
            } else {
                Stack<Integer> existedStack = piles.get(index);
                existedStack.push(array[i]);
            }
        }

        PriorityQueue<Pile<Integer>> heap = new PriorityQueue<>(piles);
        for (int c = 0; c < length; c++) {
            Pile<Integer> smallPile = heap.poll();
            array[c] = smallPile.pop();
            if (!smallPile.isEmpty()) {
                heap.offer(smallPile);
            }
        }
        assert(heap.isEmpty());
    }

    private static class Pile<E extends Comparable<? super E>> extends Stack<E> implements Comparable<Pile<E>> {
        public int compareTo(Pile<E> y) { return peek().compareTo(y.peek()); }
    }

    private static int binarySearchForPile(int elem, int pileCount, List<Pile<Integer>> piles) {

        int low = 0;
        int high = pileCount;
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (piles.get(mid).peek() > elem) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return low;
    }



    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 101; i++) {
            int maxim = i * 100;
            int[] array = new int[maxim];
            String s = "/Users/habibullinaleyla/Downloads/PatienceSort/src/main/java/tests/test";
            s += String.valueOf(i);
            s += ".txt";
            File f = new File(s);

            FileReader fr = new FileReader(f);
            Scanner sc = new Scanner(fr);

            int j = 0;
            while (sc.hasNextLine()) {
                array[j] = Integer.parseInt(sc.nextLine());
                j++;
            }
            patienceSort(array, array.length);
            fr.close();
        }

    }


}