package com.company.lab2;

import java.io.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Inversions {

    static FastScanner in;
    static PrintWriter out;

    static long merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0;
        long count = 0;
        while (i < left.length || j < right.length){
            if (i == left.length){
                arr[i+j] = right[j];
                j++;
            }
            else{
                if (j == right.length){
                    arr[i+j] = left[i];
                    i++;
                }
                else{
                    if (left[i] <= right[j]){
                        arr[i+j] = left[i];
                        i++;
                    }
                    else{
                        arr[i+j] = right[j];
                        count += left.length - i;
                        j++;
                    }
                }
            }
        }
        return count;
    }

    static long inversionsCount(int[] a){
        if(a.length < 2) {return 0;}

        int mid = (a.length )/2;
        int[] left = Arrays.copyOfRange(a, 0 , mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);

        return inversionsCount(left) + inversionsCount(right) + merge(a, left, right);
    }

    public static void main(String[] args) {
        setup("inversions.in","inversions.out");
        //Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        long count = inversionsCount(arr);
        out.print(count);
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

