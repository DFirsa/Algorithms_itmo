package com.company.lab2;

import java.io.*;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Algo {

    static FastScanner in;
    static PrintWriter out;

    static void merge(String[][] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        String L[][] = new String[n1][2];
        String R[][] = new String[n2][2];

        for (int i=0; i<n1; ++i){
            L[i][0] = arr[l + i][0];
            L[i][1] = arr[l + i][1];
        }
        for (int j=0; j<n2; ++j) {
            R[j][0] = arr[m + 1 + j][0];
            R[j][1] = arr[m + 1 + j][1];
        }
        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i][0].compareTo(R[j][0]) <= 0) {
                arr[k][0] = L[i][0];
                arr[k][1] = L[i][1];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void mergeSort(String[][] a, int left, int right){
        if (left < right)
        {
            int mid = (left+right)/2;

            mergeSort(a, left, mid);
            mergeSort(a , mid+1, right);

            merge(a, left, mid, right);
        }
    }

    static void inputArray(int n, String[][] a){
        for (int i = 0; i < n; i++){
            a[i][0] = in.next();
            a[i][1] = in.next();
        }
    }

    public static void main(String[] args) {
        setup("race.in", "race.out");

        int n = in.nextInt();

        String[][] arr = new String[n][2];

        inputArray(n, arr);

        mergeSort(arr, 0, n-1);

        out.println("=== " + arr[0][0] + " ===");
        out.println(arr[0][1]);
        for (int i = 1; i < n; i++){
            if (arr[i][0].compareTo(arr[i-1][0]) != 0 ){
                out.println("=== " + arr[i][0] + " ===");
            }
            out.println(arr[i][1]);
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
