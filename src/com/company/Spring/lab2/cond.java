package com.company.Spring.lab2;

import java.io.*;
import java.util.*;

public class cond {
    static FastScanner in;
    static PrintWriter out;

    static List<Integer>[] graph;
    static List<Integer>[] invert;
    static List<Integer> finishTime;
    static boolean[] used;
    static int[] group;
    static int groupNum = 0;

    static void read(int countEdges){
        int a,b;

        HashSet<Integer>[] edges = new HashSet[graph.length];
        for (int i = 0; i < graph.length; i++)edges[i] = new HashSet<>();

        for( int i = 0; i < countEdges; i++){
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;

            if (!edges[a].contains(b)){
                graph[a].add(b);
                invert[b].add(a);
                edges[a].add(b);
            }
        }
    }

    static void dfsInvert(int point){
        used[point] = true;

        for (int i: invert[point]){
            if (!used[i]) dfsInvert(i);
        }

        finishTime.add(point);
    }

    static void dfs(int point){
        used[point] = false;

        for (int i: graph[point]){
            if (used[i])dfs(i);
        }

        group[point] = groupNum;
    }

    static void getComp(){

        for (int i = 0; i < graph.length; i++){
            if (!used[i]) dfsInvert(i);
        }

        for (int i = finishTime.size() - 1; i >= 0; i--){
            if (used[finishTime.get(i)]){
                groupNum++;
                dfs(finishTime.get(i));
            }
        }


    }

    static void dfsSort(int point, boolean[] visited, Stack<Integer> stack){
        visited[point] = true;

        for (int i = 0; i < graph[point].size(); i++){
            if (!visited[graph[point].get(i)]){
                dfsSort(graph[point].get(i), visited, stack);
            }
        }

        stack.push(point);
    }

    static void topsort(){
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < used.length; i++){
            if (!used[i]){
                dfsSort(i, used, stack);
            }
        }

        HashMap<Integer, Integer> convert = new HashMap<>();
        groupNum = 1;
        while (!stack.empty()){
            int a = stack.pop();
            if (!convert.containsKey(group[a])){
                convert.put(group[a], groupNum);
                groupNum++;
            }
        }

        for (int i = 0; i < group.length; i++){
            out.print(convert.get(group[i]) + " ");
        }
    }

    public static void main(String[] args){
        setup("cond.in","cond.out");

        int countPoints = in.nextInt();
        int countEdges = in.nextInt();

        group = new int[countPoints];
        used = new boolean[countPoints];
        finishTime = new ArrayList();
        graph = new ArrayList[countPoints];
        invert = new ArrayList[countPoints];
        for(int i = 0; i < countPoints; i++){
            graph[i] = new ArrayList<>();
            invert[i] = new ArrayList<>();
        }
        read(countEdges);

        getComp();

        out.println(groupNum);
        topsort();

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
