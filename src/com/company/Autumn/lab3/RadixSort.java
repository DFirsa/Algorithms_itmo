package com.company.Autumn.lab3;

import java.io.*;
import java.util.StringTokenizer;

public class RadixSort {

    static FastScanner in;
    static PrintWriter out;

    static void radixSort(String[] a, int position){
        int[] countArr = new int[26];

        for (int i = 0; i < 26; i++){
            countArr[i] = 0;
        }

        String[] c = new String[a.length];

        for(int i = 0; i < a.length; i++){
            countArr[getNumOnChar(a[i].charAt(position))]++;
        }
        countArr[0]--;

        for (int i = 1; i < 26; i++){
            countArr[i] += countArr[i-1];
        }

        for (int i = a.length - 1; i >= 0; i--){
            c[countArr[getNumOnChar(a[i].charAt(position))]] = a[i];
            countArr[getNumOnChar(a[i].charAt(position))]--;
        }

         for (int i = 0; i < a.length; i++){
             a[i] = c [i];
         }
    }

    static int getNumOnChar(char symbol){
        int first = (int)'a';
        int num = (int)symbol - first;
        return num;
    }

    public static void main(String[] args){
        setup("radixsort.in","radixsort.out");
        //Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        String[] arr = new String[n];

        for(int i = 0; i < n; i++){
            arr[i] = in.next();
        }

        for(int i = 0; i < k; i++){
            radixSort(arr, m - i - 1);
        }

        for(int i = 0; i < n; i++){
            out.println(arr[i]);
        }
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
