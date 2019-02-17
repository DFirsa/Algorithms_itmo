package com.company.Spring.lab1;

import java.io.*;
import java.util.*;

public class pathpbge {

    static FastScanner in;
    static PrintWriter out;

    static List<Integer>[] graph;
    static boolean[] used;
    static int[] path;

    static void read(int countEdge){
        int a,b;
        for (int i = 0; i < countEdge; i++ ){
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;

            graph[a].add(b);
            graph[b].add(a);
        }
    }

    static void bfs(int start){

        Queue queue = new Queue(graph.length);
        queue.push(start);
        used[start] = true;

        while (!queue.isEmpty()){

            int v = queue.pop();

            for (int i = 0; i < graph[v].size(); i++){
                if (!used[graph[v].get(i)]){

                    path[graph[v].get(i)] = path[v] + 1;

                    used[graph[v].get(i)] = true;
                    queue.push(graph[v].get(i));
                }
            }
        }
    }

    public static void main(String[] args){
        setup("pathbge1.in","pathbge1.out");

        int countPoint = in.nextInt();
        int countEdge = in.nextInt();

        graph = new ArrayList[countPoint];
        for (int i = 0; i < countPoint; i++) graph[i] = new ArrayList<>();
        used = new boolean[countPoint];
        path = new int[countPoint];

        read(countEdge);

        path[0] = 0;
        bfs(0);

        for (int i = 0; i < path.length; i++) out.print(path[i] + " ");

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

    static class Queue{
        int[] queue;
        int head, tail;

        public Queue(int size){
            head = 0;
            tail = 0;
            queue = new int[size];
        }

        int pop(){
            int res = queue[head];
            head++;
            return res;
        }

        void push(int el){
            queue[tail] = el;
            tail++;
        }

        boolean isEmpty(){
            if (head < tail)return false;
            else return true;
        }
    }
}
