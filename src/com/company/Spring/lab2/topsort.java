package com.company.Spring.lab2;

import java.io.*;
import java.util.*;

public class topsort {

    static FastScanner in;
    static PrintWriter out;

    static List<Integer>[] graph;
    static short[] visited;
    static Stack<Integer> stack;
    static boolean hasCycle;


    static void read(int countEdges){
        int a,b;
        for (int i = 0; i < countEdges; i++){
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;

            graph[a].add(b);
        }
    }

    static void dfs(int point){
        visited[point] = 1;

        for (int i = 0; i < graph[point].size(); i++){

            if (visited[graph[point].get(i)] == 1){
                hasCycle = true;
                return;
            }

            if (visited[graph[point].get(i)] == 0){
                dfs(graph[point].get(i));
            }
        }
        visited[point] = 2;
        stack.push(point + 1);
    }

    static void topsort(){
        hasCycle = false;

        for (int i = 0; i < visited.length; i++){
            if (visited[i] == 0){
                dfs(i);
            }

            if (hasCycle) return;
        }
    }

    static void getResult(){

        if (hasCycle) {
            out.print(-1);
            return;
        }

        while (!stack.empty()){
            out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args){
        setup("topsort.in","topsort.out");

        int countPoints, countEdges;

        countPoints = in.nextInt();
        countEdges = in.nextInt();

        stack = new Stack<>();

        visited = new short[countPoints];
        graph = new ArrayList[countPoints];
        for (int i = 0; i < countPoints; i++){
            graph[i] = new ArrayList<>();
        }

        read(countEdges);

        topsort();

        getResult();

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
