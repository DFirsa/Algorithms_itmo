package com.company.lab4;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Brackets {


    static boolean isEmpty(int top){
        if(top == -1){return true;}
        else{return false;}
    }

    static int pop(int top){
        if(!isEmpty(top)){top--;}
        return top;
    }

    static int push(char[] stack, int top, char x){
        top++;
        stack[top] = x;
        return top;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream("brackets.in"));
        PrintWriter out = new PrintWriter("brackets.out");
//        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()){
            int top = -1;
            String brackets = scan.next();
            char[] stack = new char[brackets.length()];
            char bracket;
            for(int i = 0; i < brackets.length(); i++){
                bracket = brackets.charAt(i);
                top = push(stack, top, bracket);
                if(top >= 1 && stack[top] == ')' && stack[top - 1] == '('){
                    top = pop(top);
                    top = pop(top);
                }
                if(top >= 1 && stack[top] == ']' && stack[top - 1] == '['){
                    top = pop(top);
                    top = pop(top);
                }
            }

            if (top == -1) out.println("YES");
            else out.println("NO");
        }

        out.close();
        scan.close();
    }

}
