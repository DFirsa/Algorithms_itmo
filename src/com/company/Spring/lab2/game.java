package com.company.Spring.lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class game {
    static FastScanner in;
    static PrintWriter out;

    static List<Integer>[] graph;
    static boolean[] isWinPath;
    static boolean[] used;


    static void read(int countEdges){

        int a,b;

        for( int i = 0; i < countEdges; i++){
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;

            graph[a].add(b);
        }
    }

    static void setPosition(int point){
        used[point] = true;
        for (int i: graph[point]) {
            if (!used[i])setPosition(i);
            if (!isWinPath[i]){
                isWinPath[point] = true;
                return;
            }
        }
    }

    public static void main(String[] args){
        setup("game.in","game.out");

        int countPoint, countEdge, startPoint;
        countPoint = in.nextInt();
        countEdge = in.nextInt();
        startPoint = in.nextInt() - 1;

        graph = new ArrayList[countPoint];
        used = new boolean[countPoint];
        isWinPath = new boolean[countPoint];
        for (int i = 0; i < countPoint; i++) graph[i] = new ArrayList<>();

        read(countEdge);
        setPosition(startPoint);

        if (isWinPath[startPoint])out.print("First player wins");
        else out.print("Second player wins");

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
