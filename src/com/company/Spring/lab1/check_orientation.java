package com.company.Spring.lab1;

import java.io.*;
import java.util.StringTokenizer;

public class check_orientation {
    static FastScanner in;
    static PrintWriter out;

    static int[][] transposition(int[][] matrix){
        int[][] transpos = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                transpos[j][i] = matrix[i][j];
            }
        }

        return transpos;
    }

    static boolean isCopy(int[][] matrix, int[][] transpos){
        int count = 0;
        for (int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if (matrix[i][j] != transpos[i][j]) return false;
                if (matrix[i][j] == 1)count++;
            }
        }
        if (count % 2 != 0) return false;
        return true;
    }

    public static void main(String[] args){
        setup("input.txt","output.txt");

        int length = in.nextInt();
        int[][] matrix = new int[length][length];

        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++){
                matrix[i][j] = in.nextInt();
            }
        }

        int[][] transpos = transposition(matrix);

        if (isCopy(matrix,transpos)) out.println("YES");
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
