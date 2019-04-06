package com.company.Spring.lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class pathbgep {
    static FastScanner in;
    static PrintWriter out;

    static long[] paths;
    static ArrayList<Edge>[] graph;
    static boolean[] used;

    static class Edge{
        int to;
        int weight;

        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class Point implements Comparable<Point>{
        int pointNum;

        Point(int point){
            pointNum = point;
        }

        @Override
        public int compareTo(Point o) {
            if (paths[pointNum] == paths[o.pointNum])
                return Integer.compare(pointNum, o.pointNum);
            return Long.compare(paths[pointNum], paths[o.pointNum]);
        }
    }

    static void read(int countEdges){
        int from, to, weight;

        for (int i = 0 ; i < countEdges; i++){
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            weight = in.nextInt();

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }
    }

    static void dijkstra(){
        paths[0] = 0;

        TreeSet<Point> queue = new TreeSet<>();
        queue.add(new Point(0));
        used[0] = true;

        while (!queue.isEmpty()){
            int point = queue.pollFirst().pointNum;

            if (paths[point] == Long.MAX_VALUE)break;

            for(Edge e: graph[point]){
                if (paths[e.to] > paths[point] + e.weight){
                    if (used[e.to]){
                        Point p = new Point(e.to);
                        queue.remove(p);
                        paths[e.to] = paths[point] + e.weight;
                        queue.add(p);
                    }
                    else{
                        paths[e.to] = paths[point] + e.weight;
                        queue.add(new Point(e.to));
                        used[e.to] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        setup("pathbgep.in","pathbgep.out");

        int countPoints = in.nextInt();
        int countEdges = in.nextInt();

        paths = new long[countPoints];
        graph = new ArrayList[countPoints];
        used = new boolean[countPoints];

        for (int i = 0; i < countPoints; i++){
            graph[i] = new ArrayList<>();
            paths[i] = Long.MAX_VALUE;
        }

        read(countEdges);

        dijkstra();

        for (int i = 0; i < countPoints; i++){
            out.print(paths[i] + " ");
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
