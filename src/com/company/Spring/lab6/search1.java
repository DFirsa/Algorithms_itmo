package com.company.Spring.lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class search1 {
    static FastScanner in;
    static PrintWriter out;

    static ArrayList<Integer> startPositions;

    static void findSubstring(String origin, String substring){

        for (int i = 0; i < origin.length() - substring.length() + 1; i++){
            boolean isSubstring = true;
            for (int j = 0; j < substring.length(); j++){

                if (origin.charAt(i+j) != substring.charAt(j)){
                    isSubstring = false;
                    break;
                }
            }
            if (isSubstring) startPositions.add(i+1);
        }
    }

    public static void main(String[] args){
        setup("search1.in","search1.out");

        startPositions = new ArrayList<>();

        String substring = in.next();
        String original = in.next();

        findSubstring(original, substring);

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
