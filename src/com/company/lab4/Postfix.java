package com.company.lab4;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Postfix {

    static boolean isEmpty(int top){
        if(top == -1){return true;}
        else{return false;}
    }

    static int push(String[] stack, int top, String x){
        top++;
        stack[top] = x;
        return top;
    }

    static int pop(int top){
        if (!isEmpty(top)){
            top--;
        }
        return top;
    }

    static boolean isMult(String x){
        int n = (int)x.charAt(0) - (int)'*';
        if (n == 0){
            return true;
        }
        else{return false;}
    }

    static boolean isSubtraction(String x){
        int n = (int)x.charAt(0) - (int)'-';
        if(n == 0 && x.length() == 1){return true;}
        else{return false;}
    }

    static boolean isPlus(String x){
        int n = (int)x.charAt(0) - (int)'+';
        if(n == 0){return true;}
        else{return false;}
    }



    public static void main(String[] args)throws FileNotFoundException{
        Scanner in = new Scanner(new FileInputStream("postfix.in"));
        PrintWriter out = new PrintWriter("postfix.out");

        String[] stack = new String[100];

        int top = -1;
        while(in.hasNext()){

            top = push(stack, top, in.next());

            if(top >= 2){
                if(isSubtraction(stack[top])){
                    int n = Integer.parseInt(stack[top - 2]) - Integer.parseInt(stack[top - 1]);
                    for (int i = 0; i < 3; i++) top = pop(top);
                    top = push(stack, top, Integer.toString(n));
                }

                if(isPlus(stack[top])){
                    int n = Integer.parseInt(stack[top - 2]) + Integer.parseInt(stack[top - 1]);
                    for (int i = 0; i < 3; i++) top = pop(top);
                    top = push(stack, top, Integer.toString(n));
                }

                if (isMult(stack[top])){
                    int n = Integer.parseInt(stack[top - 2]) * Integer.parseInt(stack[top - 1]);
                    for (int i = 0; i < 3; i++) top = pop(top);
                    top = push(stack, top, Integer.toString(n));
                }
            }
        }
        out.print(Integer.parseInt(stack[top]));

        in.close();
        out.close();
    }

}
