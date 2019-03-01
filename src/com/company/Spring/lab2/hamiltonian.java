package com.company.Spring.lab2;

import java.io.*;
import java.util.*;

public class hamiltonian {
    static FastScanner in;
    static PrintWriter out;

    static List<Integer>[] graph;
    static int start;
    static boolean hasHamilton = false;
    static Stack<Integer> stack;
    static boolean isQueue = true;

    static void read(int countEdges, int countPoints){

        int a,b;

        for( int i = 0; i < countEdges; i++){
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;

            graph[a].add(b);
        }
    }

    static void dfsSort(int point, boolean[] used){
        used[point] = true;
        for (int i: graph[point]){
            if (!used[i])dfsSort(i,used);
        }
//        start = point;
        stack.push(point);
    }

    static void topSort(int countPoints){
        boolean[] used = new boolean[countPoints];
        for (int i = 0; i < countPoints; i++){
            if (!used[i])dfsSort(i,used);
        }
    }

    static boolean existHamiltonPath(){
        int[] path = new int[graph.length];
        int from = stack.pop();
        path[from] = 0;
        while (!stack.empty()){
            if (graph[from].contains(stack.peek())){
                path[stack.peek()] = path[from] + 1;
                from = stack.pop();
                if (path[from] == graph.length - 1)return  true;
            }
            else break;
        }
        return false;
    }

    public static void main(String[] args){
        setup("hamiltonian.in","hamiltonian.out");

        int countPoints, countEdges;
        countPoints = in.nextInt();
        countEdges = in.nextInt();

        if (countPoints == 1){
            out.print("YES");
            close();
            return;
        }

        stack = new Stack<>();
        graph = new ArrayList[countPoints];
        for (int i = 0 ; i < countPoints; i++) graph[i] = new ArrayList<>();

        read(countEdges,countPoints);

        topSort(countPoints);

        if (existHamiltonPath())out.print("YES");
        else out.print("NO");

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
