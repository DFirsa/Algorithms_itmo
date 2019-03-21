package com.company.Spring.lab3;

import java.io.*;
import java.util.*;

public class Spantree2 {
    static FastScanner in;
    static PrintWriter out;

    static int MAX = 2000000;
    static int[] weight;

    static class Node{
        int finish;
        int weight;

        Node(int finish, int weight){
            this.weight = weight;
            this.finish = finish;
        }
    }

    static class HeapQueue{
        int[] heap;
        int[] position;
        int size;

        HeapQueue(int countPoints){
            heap = new int[countPoints];
            position = new int[countPoints];
            size = countPoints;

            for (int i = 0; i < countPoints; i++){
                heap[i] = i;
                position[i] = i;
            }
        }

        private int rightSon(int point){
            return (2*point + 1);
        }

        private int leftSon(int point){
            return (2*point + 2);
        }

        private int parent(int point){
            return ((point - 1)/2);
        }

        private void swap(int a, int b){
            int t = heap[a];
            int tPos = position[heap[a]];

            position[heap[a]] = position[heap[b]];
            position[heap[b]] = tPos;

            heap[a] = heap[b];
            heap[b] = t;
        }

        private void siftUp(int point){
            while (point != 0){

                int parent = parent(point);

                if (weight[heap[parent]] > weight[heap[point]]){
                    swap(parent,point);
                    point = parent;
                }
                else break;
            }
        }

        private void siftDown(int point){
            while (leftSon(point) < size){
                int left = leftSon(point);
                int right = rightSon(point);

                if (right < size){
                    if (weight[heap[left]] <= weight[heap[right]] && weight[heap[left]] < weight[heap[point]]){
                        swap(left, point);
                        point = left;
                    }
                    else{
                        if (weight[heap[left]] > weight[heap[right]] && weight[heap[right]] < weight[heap[point]]){
                            swap(right,point);
                            point = right;
                        }
                        else break;
                    }
                }
                else{
                    if (weight[heap[left]] < weight[heap[point]]) {
                        swap(left, point);
                        point = left;
                    }
                    else break;
                }
            }
        }

        int poll(){
            int result = heap[0];
            swap(0, size - 1);

            size--;
            siftDown(0);
            return result;
        }

        void decreaseKey(int value){
            siftUp(position[value]);
        }

        boolean isEmpty(){
            if (size == 0)return true;
            else return false;
        }
    }

    static class Graph{

        ArrayList<Node>[] graph;
        boolean[] usedPoints;

        Graph(int countPoints){

            graph = new ArrayList[countPoints];
            usedPoints = new boolean[countPoints];
            weight = new int[countPoints];

            for (int i = 0; i < countPoints; i++){
                graph[i] = new ArrayList<>();
                weight[i] = MAX;
            }
        }

        void read(int countEdges){
            int a, b, weight;
            for (int i = 0; i < countEdges; i++){
                a = in.nextInt() - 1;
                b = in.nextInt() - 1;
                weight = in.nextInt();

                graph[a].add(new Node(b,weight));
                graph[b].add(new Node(a,weight));
            }
        }

        long getWeight(){
            weight[0] = 0;

            HeapQueue setPoints = new HeapQueue(weight.length);

            while (setPoints.size != 1){
                int v = setPoints.poll();

                usedPoints[v] = true;

                for (Node i: graph[v]){
                    if (!usedPoints[i.finish] && i.weight < weight[i.finish]){
                        weight[i.finish] = i.weight;
                        setPoints.decreaseKey(i.finish);
                    }
                }
            }

            long result = 0;
            for (int i = 0; i < weight.length; i++)result += weight[i];

            return result;
        }
    }

    public static void main(String[] args){
        setup("spantree3.in","spantree3.out");
//        setup("cycle.in","cycle.out");

        int countPoints = in.nextInt();
        int countEdges = in.nextInt();

        Graph graph = new Graph(countPoints);

        graph.read(countEdges);

        long result = graph.getWeight();

        out.print(result);

        close();
    }

    public static void setup(String inFile, String outFile) {
        try {
            in = new FastScanner(new File(inFile));
            out = new PrintWriter(new File(outFile));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(){
        out.close();
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}