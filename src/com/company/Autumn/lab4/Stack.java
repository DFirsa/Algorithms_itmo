package com.company.Autumn.lab4;

import java.io.*;
import java.util.StringTokenizer;

public class Stack {

    static FastScanner in;
    static PrintWriter out;

    static int push(int[] stack, int top, int x){
        top++;
        stack[top] = x;
        return top;
    }

    static boolean isEmpty(int top){
        if (top == -1){return true;}
        else{return false;}
    }

    static int pop(int[] stack, int top){
        if(!isEmpty(top)){
            out.println(stack[top]);
            top--;
        }
        return top;
    }

    static boolean isPluse(String commmand){
        int num = (int)commmand.charAt(0) - (int)'+';
        if(num == 0){return true;}
        else{return false;}
    }

    public static void main(String[] args){
        setup("stack.in","stack.out");
        //Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String command;
        int num;

        int[] stack = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++){
            command = in.next();
            if(isPluse(command)){
                num = in.nextInt();
                top = push(stack, top, num);
            }
            else{
                top = pop(stack, top);
            }
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
