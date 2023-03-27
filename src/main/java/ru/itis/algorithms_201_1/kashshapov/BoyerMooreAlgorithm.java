package ru.itis.algorithms_201_1.kashshapov;

import java.util.ArrayList;

public class BoyerMooreAlgorithm {

    public static int iterations;

    private static int[] preBmBc(String x) {
        int[] table = new int[26];

        for (int i = 0; i < 26; i++) {
            table[i] = x.length();
            iterations++;
        }

        for (int i = 0; i < x.length() - 1; i++) {
            table[x.charAt(i) - 'a'] = x.length() - 1 - i;
            iterations++;
        }

        return table;
    }

    private static boolean isPrefix(String x, int p) {
        int j = 0;
        for (int i = p; i < x.length(); i++) {
            if (x.charAt(i) != x.charAt(j)) {
                return false;
            }
            j++;
            iterations++;
        }
        return true;
    }

    private static int suffixLength(String x, int p) {
        int len = 0;
        int i = p;
        int j = x.length() - 1;
        while (i >= 0 && x.charAt(i) == x.charAt(j)) {
            len++;
            i--;
            j--;
            iterations++;
        }
        return len;
    }

    private static int[] preBmGs(String x) {
        int[] table = new int[x.length()];
        int lastPrefixPos = x.length();
        for (int i = x.length() - 1; i >= 0; i--) {
            if (isPrefix(x, i + 1)) {
                lastPrefixPos = i + 1;
            }
            table[x.length() - 1 - i] = lastPrefixPos - i + x.length() - 1;
            iterations++;
        }
        for (int i = 0; i < x.length() - 1; i++) {
            int slen = suffixLength(x, i);
            table[slen] = x.length() - 1 - i + slen;
            iterations++;
        }
        return table;
    }

    public static ArrayList<Integer> algorithm(String y, String x) {
        ArrayList<Integer> answer = new ArrayList<>();
        iterations = 0;
        if (x.length() == 0 || y.length() < x.length()) {
            answer.add(-1);
            iterations = 1;
            return answer;
        }

        int[] bmBc = preBmBc(x);
        int[] bmGs = preBmGs(x);
        for (int i = x.length() - 1; i < y.length(); ) {
            int j = x.length() - 1;
            while (x.charAt(j) == y.charAt(i)) {
                if (j == 0) {
                    answer.add(i);
                    iterations++;
                    break;
                }
                --i;
                --j;
                iterations++;
            }
            i += Math.max(bmGs[x.length() - 1 - j], bmBc[y.charAt(i) - 'a']);
        }
        if (answer.isEmpty()) {
            answer.add(-1);
            iterations++;
        }
        return answer;
    }
}

