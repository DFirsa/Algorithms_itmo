package com.company.Spring.lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class negcycle {
    static FastScanner in;
    static PrintWriter out;

    static class Node{
        int from;
        int to;
        int weight;

        Node(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<Node> edges;
    static ArrayList<Integer> cycle;
    static boolean[] used;
    static int[] parents;
    static long[] path;

    static void initialization(int countPoints){
        edges = new ArrayList<>();
        cycle = new ArrayList<>();
        parents = new int[countPoints];
        path = new long[countPoints];
        used = new boolean[countPoints];
    }

    static void read(int countPoints){
        int weight;

        for (int i = 0; i < countPoints; i++){
            for (int j = 0; j < countPoints; j++){
                weight = in.nextInt();
                if (i == j && weight == 0)continue;
                if (weight != 1000000000){
                    edges.add(new Node(i,j,weight));
                }
            }
        }
    }

    static void getCycle(int point){
        used[point] = true;
        cycle.add(point);

        if (!used[parents[point]])getCycle(parents[point]);
    }

    static boolean hasCycle(){

        for (int i = 0; i < path.length - 1; i++){

            boolean smthChanged = false;

            for (Node j: edges){
                 if (path[j.from] + j.weight < path[j.to]){
                        path[j.to] = Math.max(path[j.from] + j.weight, Long.MIN_VALUE);
                        parents[j.to] = j.from;
                        smthChanged = true;
                    }
            }

            if (!smthChanged){
                return false;
            }
        }

        int cyclePoint = -1;
        for (Node i: edges){
            if (path[i.to] != Long.MAX_VALUE){
                if (path[i.from] + i.weight < path[i.to]){
                    cyclePoint = i.to;
                    parents[i.to] = i.from;
                    break;
                }
            }
        }

        if (cyclePoint == -1)return false;

        for (int i = 0; i < path.length - 1; i++){
            cyclePoint = parents[cyclePoint];
        }

        getCycle(cyclePoint);
        return true;
    }

    public static void main(String[] args){
        setup("negcycle.in","negcycle.out");

        int countPoints = in.nextInt();

        initialization(countPoints);
        read(countPoints);

        if (!hasCycle()){
            out.println("NO");
            close();
            return;
        }

        out.println("YES");
        out.println(cycle.size()+1);

        for (int i = cycle.size() - 1; i >= 0; i--){
            int res = cycle.get(i) + 1;
            out.print(res + " ");
        }
        out.print(cycle.get(cycle.size() - 1) + 1);

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
