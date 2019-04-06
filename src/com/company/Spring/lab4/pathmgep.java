package com.company.Spring.lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class pathmgep {
    static FastScanner in;
    static PrintWriter out;

    static long INF = Long.MAX_VALUE;
    static int MAX = Integer.MAX_VALUE;

//    static ArrayList<Node>[] graph;
    static int[][] graph;
    static boolean[] usedPoints;
    static long[] path;

   static class Node{
        int to;
        int weight;

        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }


    static void initialization(int countPoints){
//        graph = new ArrayList[countPoints];
        graph = new int[countPoints][countPoints];
        usedPoints = new boolean[countPoints];
        path = new long[countPoints];
    }

    static void readGraph(int countPoints){
        int weight;

        for (int i = 0 ; i< countPoints; i++){
            for (int j = 0; j < countPoints; j++){
                weight = in.nextInt();
                if (weight != -1 && i != j){
                    graph[i][j] = weight;
                }
                else graph[i][j] = MAX;
            }
            path[i] = INF;
        }
    }

    static boolean dijkstra(int start, int finish){

       path[start] = 0;

       for (int i = 0; i < graph.length; i++){

           int point = -1;
           for (int j = 0; j < usedPoints.length; j++){
               if (!usedPoints[j] && (point == -1 || path[j] < path[point])){
                   point = j;
               }
           }

           if (path[point] == INF)return false;
           usedPoints[point] = true;

           if(usedPoints[finish])return true;

           for (int j = 0; j < graph[point].length; j++){
               if (graph[point][j] != MAX){
                   if (graph[point][j] + path[point] < path[j]) path[j] = path[point] + graph[point][j];
               }
           }
       }

       return true;
    }

    public static void main(String[] args){
        setup("pathmgep.in","pathmgep.out");

        int countPoints = in.nextInt();
        int startPoint = in.nextInt() - 1;
        int finishPoint = in.nextInt() - 1;

        initialization(countPoints);
        readGraph(countPoints);

        if (dijkstra(startPoint,finishPoint))out.print(path[finishPoint]);
        else out.print(-1);

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
