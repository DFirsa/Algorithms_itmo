package com.company.Spring.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class circulation {
    static FastScanner in;
    static PrintWriter out;

    static class Pair{
        int from;
        int to;
        int max;

        Pair(int from, int to, int max){
            this.from = from;
            this.to = to;
            this.max = max;
        }
    }

    static class Graph{
        int[][] graphVal;
        int finishPoint;
        long min = Long.MAX_VALUE;

        ArrayList<Pair> edges;

        Graph(int countPoints, int countEdges){
            graphVal = new int[countPoints+2][countPoints+2];
            edges = new ArrayList<>();
            finishPoint = countPoints + 1;

            read(countEdges);
        }

        void read(int countEdges){
            int from, to, min, max;

            for (int i = 0; i < countEdges; i++){
                from = in.nextInt();
                to = in.nextInt();
                min = in.nextInt();
                max = in.nextInt();

                graphVal[from][to] = max - min;
                graphVal[0][to] += min;
                graphVal[from][finishPoint] += min;

                edges.add(new Pair(from, to, max));
            }
        }

        boolean bfs(int[] parent, int countPoints){

            boolean[] used = new boolean[countPoints];

            ArrayList<Integer> queue = new ArrayList<>();
            queue.add(0);
            used[0] = true;

            while (!queue.isEmpty()){
                int point = queue.get(0);
                queue.remove(0);

                for (int i = 0; i < graphVal[point].length; i++){
                    if (!used[i] && graphVal[point][i] > 0){
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

            graphVal[parent[point]][point] -= min;
            graphVal[point][parent[point]] += min;
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
            }
        }

    }

    public static void main(String[] args){
        setup("circulation.in","circulation.out");

        int countPoints = in.nextInt();
        int countEdges = in.nextInt();

        Graph graph = new Graph(countPoints, countEdges);

        graph.getMaxFlow(countPoints+2);
        for (int  i = 0; i < graph.graphVal[0].length; i++){
            if (graph.graphVal[0][i] != 0){
                out.println("NO");
                close();
                return;
            }
        }

        out.println("YES");
        for (int i = 0; i < countEdges; i++){
            Pair data = graph.edges.get(i);
            out.println(data.max - graph.graphVal[data.from][data.to]);
        }

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
