package com.company.Spring.lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class search2 {
    static FastScanner in;
    static PrintWriter out;

    static ArrayList<Integer> startPositions;

    static void prefix(String string, int substrLength){
        int[] prefixF = new int[string.length()];

        prefixF[0] = 0;

        for (int i  = 1; i < string.length(); i++){
            int k  = prefixF[i-1];
            while (k > 0 && string.charAt(i) != string.charAt(k)){
                k = prefixF[k-1];
            }
            if (string.charAt(i) == string.charAt(k))k++;
            prefixF[i] = k;

            if (k == substrLength){
                startPositions.add(i - 2*substrLength + 1);
            }
        }

    }

    static void getSubstringPositions(String str, String substring){

        String data = substring + "#" + str;
        prefix(data, substring.length());

    }

    public static void main(String[] args){
        setup("search2.in","search2.out");

        String substring = in.next();
        String original = in.next();

        startPositions = new ArrayList<>();

        getSubstringPositions(original, substring);

        out.println(startPositions.size());
        for (int i: startPositions){
            out.print(i + " ");
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
