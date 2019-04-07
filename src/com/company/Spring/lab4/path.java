package com.company.Spring.lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class path {
    static FastScanner in;
    static PrintWriter out;

    static class Node{
        int to;
        long weight;

        Node(int to, long weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class Graph{

        long[] paths;
        ArrayList<Node>[] edges;
        boolean[] onNegCycle;

        long INF = Long.MAX_VALUE;
        long NEG_INF = Long.MIN_VALUE;

        Graph(int countPoints){
            paths = new long[countPoints];
            onNegCycle = new boolean[countPoints];
            edges = new ArrayList[countPoints];

            for (int i = 0; i < countPoints; i++){
                edges[i] = new ArrayList<>();
                paths[i] = INF;
            }
        }

        void readGraph(int countEdges){
            int from, to;
            long weight;

            for (int i = 0; i < countEdges; i++){
                from = in.nextInt() - 1;
                to = in.nextInt() - 1;
                weight = in.nextLong();

                edges[from].add(new Node(to,weight));
            }
        }

        void findCyclePoints(int point){
            onNegCycle[point] = true;

            for (Node i: edges[point]){
                if (!onNegCycle[i.to])findCyclePoints(i.to);
            }
        }

        void fordBellmanAlgorithm(int start){
            paths[start] = 0;

            for (int i = 0; i < paths.length - 1; i++){
                boolean changedSmth = false;
                for (int j = 0; j < edges.length; j++){
                    if (paths[j] < INF){
                        for (Node k: edges[j]){
                            if (paths[j] + k.weight < paths[k.to]){
                                paths[k.to] = Math.max(NEG_INF, paths[j] + k.weight);
                                changedSmth = true;
                            }
                        }
                    }
                }

                if (!changedSmth) return;
            }

            ArrayList<Integer> cyclePoints = new ArrayList<>();

            for (int j = 0; j < edges.length; j++){
                if (paths[j] < INF){
                    for (Node k: edges[j]){
                        if (paths[j] + k.weight < paths[k.to]){
                            cyclePoints.add(k.to);
                        }
                    }
                }
            }

            if (cyclePoints.isEmpty())return;

            for (int i: cyclePoints){
                if (!onNegCycle[i])findCyclePoints(i);
            }
        }

        void writeAnswer(){

            for (int i = 0; i < paths.length; i++){
                if (paths[i] == INF){
                    out.println("*");
                    continue;
                }

                if (onNegCycle[i] || paths[i] < -5*Math.pow(10,18)){
                    out.println("-");
                    continue;
                }

                out.println(paths[i]);
            }
        }
    }

    public static void main(String[] args){
        setup("path.in","path.out");
//        setup("cycle.in","cycle.out");

        int countPoints = in.nextInt();
        int countEdges = in.nextInt();
        int startPoint = in.nextInt() - 1;

        Graph graph = new Graph(countPoints);
        graph.readGraph(countEdges);

        graph.fordBellmanAlgorithm(startPoint);

        graph.writeAnswer();

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

        long nextLong(){
            return Long.parseLong(next());
        }
    }
}