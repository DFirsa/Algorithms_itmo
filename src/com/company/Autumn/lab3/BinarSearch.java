package com.company.Autumn.lab3;

import java.io.*;
import java.util.StringTokenizer;

public class BinarSearch {

    static FastScanner in;
    static PrintWriter out;

    static int binSearchFirst(int[] a, int n){
        int res = -2;
        int left = 0;
        int right = a.length - 1;
        int mid;
        while (left <= right){
            mid = (left + right)/2;
            if(a[mid] == n){
                res = mid;
                right = mid - 1;
            }
            if(a[mid] < n){ left = mid + 1;}
            if(a[mid] > n){right = mid - 1;}
        }
        res++;
        return res;
    }

    static int binSearchLast(int[] a, int n){
        int res = -2;
        int left = 0;
        int right = a.length - 1;
        int mid;
        while (left <= right){
            mid = (left + right)/2;
            if(a[mid] == n){
                res = mid;
                left = mid + 1;
            }
            if(a[mid] > n){right = mid - 1;}
            if(a[mid] < n){left = mid + 1;}
        }
        res++;
        return res;
    }

    static void binSearch(int[] a, int n){
        int first = binSearchFirst(a, n);
        int last;
        if(first != -1){
            last = binSearchLast(a, n);
        }
        else {
            last = -1;
            }

        out.println(first + " " + last);
    }

    public static void main(String[] args){
        setup("binsearch.in","binsearch.out");
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        int[] find = new int[k];

        for(int i = 0; i < k; i++){
            find[i] = in.nextInt();
        }

        for (int i = 0; i < k; i++){
            binSearch(arr, find[i]);
        }

        close();
    }

    public static void setup(String inFile, String outFile) {
        try {
            in = new BinarSearch.FastScanner(new File(inFile));
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
