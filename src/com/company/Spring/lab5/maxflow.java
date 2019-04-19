package com.company.Spring.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class maxflow {
    static FastScanner in;
    static PrintWriter out;

    static class Graph{
        ArrayList<Integer>[] graph;
        int[][] graphVal;
        long flow = 0;
        int finishPoint;
        long min = Long.MAX_VALUE;

        Graph(int countPoints, int countEdges){
            graph = new ArrayList[countPoints];
            graphVal = new int[countPoints][countPoints];
            finishPoint = countPoints - 1;
            for (int i = 0; i < countPoints; i++){
                graph[i] = new ArrayList<>();
            }

            read(countEdges);
        }

        void read(int countEdges){
            int from, to, weight;

            for (int i = 0; i < countEdges; i++){
                from = in.nextInt() - 1;
                to = in.nextInt() - 1;
                weight = in.nextInt();

                if (from != to){
                    graph[from].add(to);
                    graphVal[from][to] = weight;
                }
            }
        }

        boolean bfs(int[] parent, int countPoints){

            boolean[] used = new boolean[countPoints];

            ArrayList<Integer>  queue = new ArrayList<>();
            queue.add(0);
            used[0] = true;

            while (!queue.isEmpty()){
                int point = queue.get(0);
                queue.remove(0);

                for (int i: graph[point]){
                    if (!used[i] && graphVal[point][i] > 0){
                        queue.add(i);
                        parent[i] = point;
                        used[i] = true;
                    }
                }
            }

            if (used[finishPoint])return true;
            else return false;
        }

        void decreasePaths(int point, int[] parent){
            if (point == 0)return;

            graphVal[parent[point]][point] -= min;
            decreasePaths(parent[point], parent);
        }

        void getMin(int point, int[] parent){
            if (point == 0)return;

            if (min > graphVal[parent[point]][point]){
                min = graphVal[parent[point]][point];
            }

            getMin( parent[point], parent);
        }

        void getMaxFlow(int countPoints){

            int[] parent = new int[countPoints];

            while (true){
                boolean hasPath = bfs(parent, countPoints);

                if (!hasPath) break;

                min = Long.MAX_VALUE;

                getMin(finishPoint, parent);
                decreasePaths(finishPoint, parent);

                flow += min;
            }
        }

    }

    public static void main(String[] args){
        setup("maxflow.in","maxflow.out");

        int countPoints = in.nextInt();
        int countEdges = in.nextInt();

        Graph graph = new Graph(countPoints,countEdges);

        graph.getMaxFlow(countPoints);

        out.println(graph.flow);

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