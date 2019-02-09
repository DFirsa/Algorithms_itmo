package com.company.Autumn.lab3;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HeapSort {

    static FastScanner in;
    static PrintWriter out;

    static int leftSon(int n){
        int son = 2*n + 1;
        return son;
    }

    static int rightSon(int n){
        int son = 2*n + 2;
        return son;
    }

    static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void createHeap(int[] a){
        for(int i = a.length/2 - 1; i >= 0 ; i--){
            heapify(a, i, a.length);
        }
    }

    static void heapSort(int[] a){
        //System.out.println(a.length - 1);
        for (int i = a.length - 1; i > 0; i--){
            swap(a,0, i);
            heapify(a, 0, i);
        }
        for (int i = 0; i < a.length; i++) System.out.print("");
    }

    static void heapify(int[] a, int i, int length){
        int n = length/2 - 1;
        while (i <= n){
            if(leftSon(i) == length - 1){
                if(a[i] > a[leftSon(i)]){break;}
                else{
                    swap(a, i,leftSon(i));
                    i = leftSon(i);
                }
            }
            else{
                if(a[i] > a[leftSon(i)] && a[i] > a[rightSon(i)]){break;}
                else{
                    if(a[leftSon(i)] > a[rightSon(i)]){
                        swap(a, i, leftSon(i));
                        i = leftSon(i);
                    }
                    else{
                        swap(a, i, rightSon(i));
                        i = rightSon(i);
                    }
                }
            }
        }
    }

    public static void main(String[] args){
//        setup("sort.in","sort.out");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        createHeap(arr);
        heapSort(arr);

        for(int i = 0; i < arr.length; i++){
            out.print(arr[i] + " ");
        }

//        close();
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
