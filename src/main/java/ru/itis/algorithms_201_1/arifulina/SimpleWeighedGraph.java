package ru.itis.algorithms_201_1.arifulina;

import java.util.ArrayList;
import java.util.Random;

public class SimpleWeighedGraph {
    public final static Random RAND = new Random();
    public ArrayList<Vertex> vertices;
    public ArrayList<Edge> edges;
    public int[][] adjacencyMatrix;

    public static class Vertex{
        public int number;
        public Vertex(int number){
            this.number = number;
        }
    }
    public static class Edge implements Comparable<Edge> {
        public int inc1;
        public int inc2;
        public int weight;
        public Edge(int inc1, int inc2, int weight){
            this.inc1 = inc1;
            this.inc2 = inc2;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
        @Override
        public String toString(){
            return inc1 + " " + inc2 + " " + weight;
        }
    }
    public void addEdge(String[] data){
        if (data.length < 3){
            return;
        }
        int inc1 = Integer.parseInt(data[0]);
        int inc2 = Integer.parseInt(data[1]);
        int weight = Integer.parseInt(data[2]);
        edges.add(new Edge(inc1, inc2, weight));
    }
    public SimpleWeighedGraph(int[][] adjacencyMatrix){
        this.adjacencyMatrix = adjacencyMatrix;
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
        int n = adjacencyMatrix.length;
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                if (adjacencyMatrix[i][j] != 0){
                    edges.add(new Edge(i, j, adjacencyMatrix[i][j]));
                }
            }
            vertices.add(new Vertex(i));
        }
    }
    public SimpleWeighedGraph(int amountOfVertices){
        this.adjacencyMatrix = new int[amountOfVertices][amountOfVertices];
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
        for (int i = 0; i < amountOfVertices; i++){
            vertices.add(new Vertex(i));
        }
    }
    public void toConnected(){
        int[] states = new int[adjacencyMatrix.length];
        states[0] = 1;
        int flag0 = states.length - 1;
        int flag1 = 1;
        int flag2 = 0;
        int i;
        while (flag1 != 0){
            i = 0;
            while (i < states.length){
                if (states[i] == 1) {states[i] = 2; flag1--; flag2++; break;}
                i++;
            }
            for (int j = 0; j < states.length; j++){
                if (adjacencyMatrix[i][j] != 0 && states[j] == 0){states[j] = 1; flag1++; flag0--;}
            }
        }
        while (flag0 != 0){
            for (int m = 0; m < states.length; m++){
                if (states[m] == 0){
                    adjacencyMatrix[0][m] = RAND.nextInt(1, 101);
                    adjacencyMatrix[m][0] = adjacencyMatrix[0][m];
                    edges.add(new Edge(0, m, adjacencyMatrix[0][m]));
                    states[m] = 2; flag0--; flag2++;
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return edges.toString() + "  n = " + vertices.size();
    }
}
