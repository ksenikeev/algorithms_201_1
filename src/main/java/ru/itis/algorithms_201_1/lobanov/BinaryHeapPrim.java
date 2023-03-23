package ru.itis.algorithms_201_1.lobanov;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryHeapPrim extends PrimAlgorithm {
    private final List<Edge>[] adjacencyList;
    private final Random random;
    private final Measurement binaryHeapPrimMeasurement;

    public BinaryHeapPrim(ArrayList<Edge>[] adjacencyList, Measurement binaryHeapPrimMeasurement) {
        this.adjacencyList = adjacencyList;
        super.numOfV = adjacencyList.length;
        this.random = new Random();
        this.binaryHeapPrimMeasurement = binaryHeapPrimMeasurement;
        super.MST = buildMST();
    }

    private int[][] buildMST() {
        int[][] MST = new int[numOfV][numOfV];
        boolean[] selectedNodes = new boolean[numOfV];
        int node = random.nextInt(numOfV);
        selectedNodes[node] = true;
        BinaryHeap<Edge> binaryHeap = new BinaryHeap<>();

        binaryHeapPrimMeasurement.startMeasurement();
        List<Edge> listOfFirstNode = adjacencyList[node];
        for (Edge edge : listOfFirstNode) {
            binaryHeapPrimMeasurement.addIteration();
            binaryHeap.add(edge);
        }
        List<Edge> list = new ArrayList<>();
        while (!binaryHeap.isEmpty()) {
            Edge edge = binaryHeap.peek();
            list.add(edge);
            int node2 = edge.getNode2();
            List<Edge> arrayList = adjacencyList[node2];
            for (Edge e : arrayList) {
                binaryHeapPrimMeasurement.addIteration();
                if (!selectedNodes[e.getNode2()]) {
                    binaryHeap.add(e);
                } else {
                    binaryHeap.remove(e);
                }
            }
            selectedNodes[node2] = true;
        }
        int iterations =  binaryHeap.getCountOfIterations();
        binaryHeapPrimMeasurement.addIteration(iterations);
        binaryHeapPrimMeasurement.stopMeasurement();

        for (Edge edge : list) {
            int node1 = edge.getNode1();
            int node2 = edge.getNode2();
            int weight = edge.getWeight();
            MST[node1][node2] = weight;
        }
        return MST;
    }

    public Measurement getBinaryHeapPrimMeasurement() {
        return binaryHeapPrimMeasurement;
    }
}
