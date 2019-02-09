package com.company.Autumn.lab4;

import java.io.*;
import java.util.StringTokenizer;

public class Queue {

    static FastScanner in;
    static PrintWriter out;

    static int put(int[] queue,int tail, int x){
        queue[tail] = x;
        tail++;
        return tail;
    }

    static int get(int[] queue, int head, int tail){
        if(tail - head > 0){
            out.println(queue[head]);
            head++;
        }
        return head;
    }

    static boolean isPlus(String commmand){
        int num = (int)commmand.charAt(0) - (int)'+';
        if(num == 0){return true;}
        else{return false;}
    }

    public static void main(String[] args){
        setup("queue.in","queue.out");
        int head = 0;
        int tail = 0;

        //Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] queue = new int[n];
        String command;
        int num;
        for(int i = 0; i < n; i++){
            command = in.next();
            if(isPlus(command)){
                num = in.nextInt();
                tail = put(queue, tail, num);
            }
            else{head = get(queue, head, tail);}
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