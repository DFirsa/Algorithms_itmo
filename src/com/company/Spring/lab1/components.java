package com.company.Spring.lab1;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class components {

    static FastScanner in;
    static PrintWriter out;

    static int groupNum = 0;

    static int findRoot(int i, int[] arr){
        while(arr[i] != i) i = arr[i];
        return i;
    }

    static void rewrite(int a, int b, int[] arr){
        if (arr[a] == arr[b])return;

        int aRoot = findRoot(a, arr);
        int bRoot = findRoot(b, arr);

        int transit;
        if(aRoot > bRoot){
            while (a != aRoot){
                transit = arr[a];
                arr[a] = bRoot;
                a = transit;
            }
            arr[aRoot] = bRoot;
        }
        else{
            while (b != bRoot){
                transit = arr[b];
                arr[b] = aRoot;
                b = transit;
            }
            arr[bRoot] = aRoot;
        }
    }

    static int rationing(int[] arr){

        HashMap<Integer, Integer> unique = new HashMap<>();

        for (int i = 0; i < arr.length; i++){
            if (!unique.containsKey(arr[i])){
                groupNum++;
                unique.put(arr[i],groupNum);
                arr[i] = groupNum;
            }
            else{
                arr[i] = unique.get(arr[i]);
            }
        }

        return unique.size();
    }

    static void findComponents(int countPoint, int countEdge){

        int[] points = new int[countPoint];
        for (int i = 0; i < countPoint; i++) points[i] = i;

        int a,b;
        for (int i = 0; i < countEdge; i++){
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            rewrite(a, b, points);
        }

        for (int i = 0; i < countPoint; i++){
            if (points[i] != i) points[i] = findRoot(i,points);
        }

        for (int i = 0; i < countPoint; i++) points[i]++;

        out.println(rationing(points));

        for (int i = 0; i < countPoint; i++){
            out.print(points[i] + " ");
        }
    }

    public static void main(String[] args){
        setup("components.in","components.out");

        findComponents(in.nextInt(), in.nextInt());

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
