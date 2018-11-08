package com.company.lab2;

import java.io.*;

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AntiQuickSort {

    static FastScanner in;
    static PrintWriter out;

    static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        //setup("antiqs.in","antiqs.out");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] arr = new int[n];
        //заполнение массива значениями от 1 до n
        for (int i = 0; i < n; i++){
            arr[i] = i+1;
        }
        //пробегаемся по маасиву и меняем местами текущий и средний от текущего эллементы (i и i/2). По сути процедура обратная partition в qSort
        for (int i = 0; i < n; i++){
            swap(arr, i, i/2);
        }
        //выввод
        for (int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }

        //close();
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
