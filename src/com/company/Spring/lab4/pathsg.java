package com.company.Spring.lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class pathsg {
    static FastScanner in;
    static PrintWriter out;

    static long[][] graph;
    static long INF = 10001;

    static void read(int countEdges){

        int from, to, weight;

        for (int i = 0; i < countEdges; i++){
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            weight = in.nextInt();

            graph[from][to] = weight;
        }
    }

    static void floyd(int countPoints){

        for (int i = 0; i < countPoints; i++){
            for (int j = 0; j < countPoints; j++){
                for (int k = 0; k < countPoints; k++){
                    if (graph[i][k] != INF && graph[j][i] != INF){
                        if (graph[j][k] > graph[i][k] + graph[j][i]){
                            graph[j][k] = graph[i][k] + graph[j][i];
                        }
                    }
                }
            }
        }
    }

    static void printResult(int countPoints){
        for (int i = 0; i < countPoints; i++){
            for (int j = 0; j < countPoints; j++){
                out.print(graph[i][j] + " ");
            }
            out.println();
        }
    }

    public static void main(String[] args){
        setup("pathsg.in","pathsg.out");

        int countPoints = in.nextInt();
        int countEdges = in.nextInt();

        graph = new long[countPoints][countPoints];

        for (int i  = 0; i < countPoints; i++){
            for (int j = 0; j < countPoints; j++){
                if (i == j)graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }

        read(countEdges);
        floyd(countPoints);
        printResult(countPoints);

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
