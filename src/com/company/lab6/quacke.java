package com.company.lab6;

public class quacke {

    static int plus(int x, int y){
        int res = x + y;
        return res;
    }

    static int minus(int x, int y){
        int res = x -y;
        return res;
    }

    static int devide(int x, int y){
        int res = x / y;
        return res;
    }

    static int modulo(int x,int y){
        int res = x % y;
        return res;
    }

    static void putReg(int x, int[] registers, char symbol){
        registers[(int)symbol - (int)'a'] = x;
    }



}
