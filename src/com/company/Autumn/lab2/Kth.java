package com.company.Autumn.lab2;

import java.io.*;

import java.util.StringTokenizer;

public class Kth {

    static FastScanner in;
    static PrintWriter out;

    static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void finderKth(int[] a, int l, int r, int k){

        int i = l;
        int j = r;
        int num_key = (l + r)/2;
        int key = a[num_key];
        while (i <= j){
            while (a[i] < key){i++;}
            while (a[j] > key){j--;}
            if (i <= j){
                swap(a, i, j);
                i++;
                j--;
            }
        }

        /*for (int q =0; q < a.length; q++){
            System.out.print(a[q] + " ");
        }
        System.out.println();*/
        if(l < j && k <= j){
            finderKth(a, l, j, k);
        }
        if(i < r && k > j){
            finderKth(a, j+1, r, k);
        }
    }

    public static void main(String[] args) {
        setup("kth.in","kth.out");
        //Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        k--;
        int A = in.nextInt();
        int B = in.nextInt();
        int C = in.nextInt();

        int[] arr = new int[n];

        arr[0] = in.nextInt();
        arr[1] = in.nextInt();

        for (int i = 2; i < n; i++){
            arr[i] = A*arr[i-2] + B*arr[i-1] + C;
        }

        finderKth(arr, 0, n-1, k);
        out.print(arr[k]);

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