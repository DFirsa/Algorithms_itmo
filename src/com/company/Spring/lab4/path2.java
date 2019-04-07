package com.company.Spring.lab4;

import java.io.*;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class path2 {
    static FastScanner in;
    static PrintWriter out;

    static long[] path;
    static ArrayList<Edge> edges;
    static long INF  = Long.MAX_VALUE;
    static long NEG_INF = Long.MIN_VALUE;

    static class Edge{
        int from;
        int to;
        long weight;

        Edge(int from, int to, long weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static void read(int countEdges){
        int from, to;
        long weight;

        for (int i = 0 ; i < countEdges; i++){
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            weight = in.nextLong();

            edges.add(new Edge(from, to ,weight));
        }
    }

    static void findPath(int startPoint){
        path[startPoint] = 0;

        for (int i = 0; i < path.length - 1; i++){
            for (Edge e: edges){
                if (path[e.from] < INF && path[e.from] > NEG_INF){
                    if (path[e.to] > path[e.from] + e.weight){
                        path[e.to] = Math.max(NEG_INF, path[e.from] + e.weight);
                    }
                }
            }
        }



        for (int i = 0; i < path.length; i++){
            for (Edge e: edges){
                if (path[e.from] < INF && path[e.from] > NEG_INF){
                    if (path[e.to] > path[e.from] + e.weight){
                        path[e.to] = NEG_INF;
                    }
                }
                if (path[e.from] == NEG_INF)path[e.to] = NEG_INF;
            }
        }
    }

    static void output(){
        for (int i = 0 ; i < path.length; i++){
            if (path[i] == INF){
                out.println("*");
                continue;
            }

            if (path[i] < -5*Math.pow(10,18)){
                out.println("-");
                continue;
            }

            out.println(path[i]);
        }
    }

    public static void main(String[] args) {
        setup("path.in","path.out");
//        setup("cycle.in", "cycle.out");

        int countPoints = in.nextInt();
        int countEdges = in.nextInt();
        int startPoint = in.nextInt() - 1;

        path = new long[countPoints];
        edges = new ArrayList<>();

        for (int i = 0; i < countPoints; i++) path[i] = INF;

        read(countEdges);

       findPath(startPoint);

        output();

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
