package ru.itis.algorithms_201_1.lobanov;

public class PrimTester {
    public static void main(String[] args) {
        int[][] G = new int[][] {
                { 0, 9, 75, 0, 0 },
                { 9, 0, 95, 19, 42 },
                { 75, 95, 0, 51, 66 },
                { 0, 19, 51, 0, 31 },
                { 0, 42, 66, 31, 0 }
        };
        int[][] J = new int[][] {
                {0, 3, 5, 2, 0},
                {3, 0, 2, 1, 6},
                {5, 2, 0, 0, 0},
                {2, 1, 0, 0, 4},
                {0, 6, 0, 4, 0}
        };
        AdjacencyMatrixPrim prim = new AdjacencyMatrixPrim(G);
        prim.printMST();
        prim.printMSTMatrix();

        BinaryHeapPrim pr = new BinaryHeapPrim(G);
        pr.printMST();
        pr.printMSTMatrix();
    }
}
