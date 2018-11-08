package com.company.lab3;

import java.io.*;

import java.util.Scanner;
import java.util.StringTokenizer;

public class IsHeap {

    static FastScanner in;
    static PrintWriter out;

    static int leftSon(int i){
        int son =2*i + 1;
        return son;
    }

    static int rightSon(int i){
        int son = 2*i + 2;
        return son;
    }

    static boolean isHeap(int[] a){
        int end = a.length/2 - 1;
        boolean isHeap = true;
        for (int i = end; i >= 0; i--){
            if (!(a[leftSon(i)] >= a[i])){
                isHeap = false;
                break;
            }
            if(a.length - 1 != leftSon(i)){
                if(!(a[rightSon(i)] >= a[i])){
                    isHeap = false;
                    break;
                }
            }
        }
        return isHeap;
    }

    public static void main(String[] args){
        setup("isheap.in","isheap.out");
        //Scanner in = new Scanner(System.in);
        int n =in.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        if (isHeap(arr) == true){ out.println("YES");}
        else {out.print("NO");}

        close();
    }


    public static void setup(String inFile, String outFile) {
        try {
            in = new  FastScanner(new File(inFile));
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
