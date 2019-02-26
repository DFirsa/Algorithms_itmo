package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class isBin {
    static FastScanner in;
    static PrintWriter out;

    static List<Integer>[] graph;
    static short[] target;
    static boolean isBin;

    static void read(int countEdges, int countPoints){
        int a,b;

        HashSet<Integer>[] edges = new HashSet[countPoints];
        for (int i = 0; i < countPoints; i++) edges[i] = new HashSet<>();

        for (int i = 0; i < countEdges; i++){
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;

            if (!edges[a].contains(b)){
                graph[a].add(b);
                graph[b].add(a);
                edges[a].add(b);
                edges[b].add(a);
            }
        }
    }

    static short invert(short c){
        if (c == 1) return 2;
        else return 1;
    }

    static void dfs(int point, short color){
        target[point] = color;

        for (int i = 0; i < graph[point].size(); i++){
            if (target[graph[point].get(i)] == 0) dfs(graph[point].get(i), invert(color));
            else
            if (target[graph[point].get(i)] == color) isBin = false;

        }
    }

    static void check(){
        for (int i = 0; i < graph.length; i++){
            if (target[i] == 0) dfs(i, (short) 1);
        }
    }

    public static void main(String[] args){
        setup("bipartite.in","bipartite.out");

        isBin = true;

        int countPoints = in.nextInt();
        int countEdges = in.nextInt();

        graph = new ArrayList[countPoints];
        target = new short[countPoints];
        for (int i = 0; i < countPoints; i++){
            graph[i] = new ArrayList<>();
            target[i] = 0;
        }

        read(countEdges, countPoints);
        check();

        if (isBin)out.println("YES");
        else out.println("NO");

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
