package com.company.Spring.lab1;

import java.io.*;
import java.util.StringTokenizer;

public class existsParallelEdge {
    static FastScanner in;
    static PrintWriter out;

    static int[][] createTable(int point_count, int edge_count){
        int[][] matrix = new int[point_count][point_count];

        int a, b;

        for (int i = 0; i < point_count; i++){
            for (int j = 0; j < point_count; j++){
                matrix[i][j] = 0;
            }
        }

        for (int i = 0; i < edge_count; i++){
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            matrix[a][b]++;
            matrix[b][a]++;
        }
        return matrix;
    }

    static boolean existsParallel(int[][] matrix){
        for (int i = 0 ; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                if (matrix[i][j] > 1) return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        setup("input.txt","output.txt");

        int countPoint = in.nextInt();
        int countEdge  = in.nextInt();

        if (existsParallel(createTable(countPoint, countEdge)))out.println("YES");
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
