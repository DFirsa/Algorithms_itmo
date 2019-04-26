package com.company.Spring.lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class prefix {
    static FastScanner in;
    static PrintWriter out;

    static ArrayList<Integer> prefix;

    static void prefix(String string){
        int[] prefixF = new int[string.length()];

        prefixF[0] = 0;

        for (int i  = 1; i < string.length(); i++){
            int k  = prefixF[i-1];
            while (k > 0 && string.charAt(i) != string.charAt(k)){
                k = prefixF[k-1];
            }
            if (string.charAt(i) == string.charAt(k))k++;
            prefixF[i] = k;
        }

        for (int i: prefixF){
            out.print(i + " ");
        }
    }



    public static void main(String[] args){
        setup("prefix.in","prefix.out");

        String str = in.next();
        prefix(str);

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
