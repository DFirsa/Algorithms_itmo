package com.company;

import java.io.*;
import java.util.*;

public class Cycle {
    static FastScanner in;
    static PrintWriter out;

    static List<Integer>[] graph;
    static short[] visited;
    static boolean hasCycle;
    static Stack<Integer> stack;
    static HashSet<Integer>[] edges;
    static int cycleValue = -1;

    static void read(int countEdges){

        int a,b;

        for (int i = 0; i < countEdges; i++){
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;

            if (!edges[a].contains(b)){
                graph[a].add(b);
                edges[a].add(b);
            }
        }
    }

    static void dfs(int point){
        visited[point] = 1;
        stack.push(point + 1);

        for (int i = 0; i < graph[point].size(); i++){

            if (visited[graph[point].get(i)] == 1 && !hasCycle){
                hasCycle = true;
                cycleValue = graph[point].get(i);
                return;
            }

            if (visited[graph[point].get(i)] == 0 && !hasCycle) dfs(graph[point].get(i));
            if (hasCycle) return;
        }
        visited[point] = 2;
    }

    static void findCycle(){

        stack = new Stack<>();
        hasCycle = false;

        for (int i = 0; i < graph.length; i++){

            if (visited[i] == 0) dfs(i);
            if (hasCycle) break;
        }

        if (hasCycle){
            List<Integer> path = new ArrayList<>();
            int top;
            while (true){
                top = stack.pop();
                path.add(top);
                if (top == cycleValue + 1) break;
            }

            out.println("YES");

            out.print(path.get(0) + " ");
            for (int i = path.size() - 1; i > 0; i--){
                if (visited[path.get(i) - 1] == 1) out.print(path.get(i) + " ");
            }
        }
        else out.println("NO");

    }

    public static void main(String[] args){
        setup("cycle.in","cycle.out");

        int countPoints, countEdges;

        countPoints = in.nextInt();
        countEdges = in.nextInt();


        visited = new short[countPoints];
        edges = new HashSet[countPoints];
        graph = new ArrayList[countPoints];
        for (int i = 0; i < countPoints; i++){
            graph[i] = new ArrayList<>();
            edges[i] = new HashSet<>();
        }

        read(countEdges);

        findCycle();

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