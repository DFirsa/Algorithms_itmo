package com.company.lab3;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Garland {

    static FastScanner in;
    static PrintWriter out;

    static boolean canLampExist(double first, double second, int n){
        double min = first;
        double ithLamp;
        for (int i = 2; i < n; i++){
            ithLamp = 2*second - first + 2;
            if (ithLamp < min){min = ithLamp;}
            first = second;
            second = ithLamp;
        }
        if(min < 0){return false;}
        else{return true;}
    }

    static double secondHeight(double firstLamp, int n){
        double up = firstLamp;
        double low = 0;
        double mid ;

        while (true){
            mid = (low + up)/2;
            if(mid == up || mid == low){break;}

            if(canLampExist(firstLamp, mid, n)){up = mid;}
            else{low = mid;}
        }

        return mid;
    }

    static double lastLampHeight(double first, double second, int n){
        double result = first;
        for (int i = 2; i < n; i++){
            result = 2*second - first + 2;
            first = second;
            second = result;
        }
        return result;
    }

    public static void main(String[] args){
        setup("garland.in","garland.out");

        int n = in.nextInt();
        double firstLampHeight = Double.parseDouble(in.next());

        double secondLampHeight = secondHeight(firstLampHeight, n);
        double result = lastLampHeight(firstLampHeight, secondLampHeight, n);

        out.print(result);
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
