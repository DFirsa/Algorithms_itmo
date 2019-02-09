package com.company.Spring.lab1;

import java.io.*;
import java.util.StringTokenizer;

public class matrix {

    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args){
        setup("input.txt","output.txt");

        int point_count = in.nextInt();
        int edge_count = in.nextInt();
        int a, b;

        int[][] matrix = new int[point_count][point_count];

        for (int i = 0; i < point_count; i++){
            for (int j = 0; j < point_count; j++) matrix[i][j] = 0;
        }

        for (int i = 0; i < edge_count; i++){
            a = in.nextInt();
            b = in.nextInt();

            matrix[a - 1][b - 1]++;
        }

        for (int i = 0; i < point_count; i++){
            for (int j = 0; j < point_count; j++){
                out.print(matrix[i][j] + " ");
            }
            out.println();
        }

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
