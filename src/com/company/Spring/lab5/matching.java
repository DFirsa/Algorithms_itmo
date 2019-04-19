package com.company.Spring.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class matching {
    static FastScanner in;
    static PrintWriter out;

    static class Graph{
        boolean[][] graph;

        long countPair = 0;
        int finishPoint;

        Graph(int left, int right, int countEdges){
            graph = new boolean[left+right+2][left+right+2];
            finishPoint = left+right+1;

            read(countEdges, left);

            for (int i = 1; i <= left; i++){
                graph[0][i] = true;
            }

            for (int i = left +1; i < finishPoint; i++){
                graph[i][finishPoint] = true;
            }

        }

        void read(int countEdges, int left){
            int from, to;

            for (int i = 0; i < countEdges; i++){
                from = in.nextInt();
                to = in.nextInt() + left;

                graph[from][to] = true;
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

                for (int i = 0; i < graph[point].length; i++){
                    if (!used[i] && graph[point][i]){
                        queue.add(i);
                        parent[i] = point;
                        used[i] = true;
                    }
                }
            }

            return used[finishPoint];
        }

        void decreasePaths(int point, int[] parent){
            if (point == 0)return;

            graph[parent[point]][point] = false;
            graph[point][parent[point]] = true;
            decreasePaths(parent[point], parent);
        }

        void getMaxFlow(int countPoints, int left){

            int[] parent = new int[countPoints];

            while (true){
                boolean hasPath = bfs(parent, countPoints);

                if (!hasPath) break;

                decreasePaths(finishPoint, parent);

                countPair++;
            }
        }
    }

    public static void main(String[] args){
        setup("matching.in","matching.out");

        int left = in.nextInt();
        int right = in.nextInt();
        int countEdges = in.nextInt();

        Graph graph = new Graph(left, right, countEdges);
        graph.getMaxFlow(left+right+2,left);

        out.print(graph.countPair);

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
