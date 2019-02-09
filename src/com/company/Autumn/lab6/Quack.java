package com.company.Autumn.lab6;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.*;

public class Quack {

    static PrintWriter out;

    static class Interpreter{
        Deque<Integer> queue = new LinkedList<>();
        int[] registry = new int[26];
        Map<String, Integer> labels = new HashMap<>();

        int get(){
            return queue.removeFirst();
        }

        void put(int x){
            queue.addLast(x & 65535);
        }

        void putReg(char reg, int x){
            registry[(int)reg - (int)'a'] = x;
        }

        private int getReg(char reg){
            return registry[(int)reg - (int)'a'];
        }

        void run(List<String> input){
            for (int i = 0; i < input.size(); i++) {
                String line = input.get(i);
                if(line.equals("+")){
                    int x = get();
                    int y = get();
                    int res = x + y;
                    put(res);
                } else if(line.equals("-")){
                    int x = get();
                    int y = get();
                    int res = x - y;
                    put(res);
                } else if(line.equals("*")){
                    int x = get();
                    int y = get();
                    int res = x * y;
                    put(res);
                } else if(line.equals("/")){
                    int x = get();
                    int y = get();
                    if (y == 0)put(0);
                    else put(x / y);
                } else if(line.equals("%")) {
                    int x = get();
                    int y = get();
                    if (y == 0)put(0);
                    else put(x % y);
                } else if(line.charAt(0) == '>'){
                    putReg(line.charAt(1), get());
                } else if(line.charAt(0) == '<'){
                    put(getReg(line.charAt(1)));
                } else if (line.equals("P")){
                    out.println(get());
                }
                else if(line.charAt(0) == 'P'){
                    out.println(getReg(line.charAt(1)));
                }else if (line.equals("C")){
                    int x = get();
                    char c = (char) (x % 256);
                    out.print(c);
                }
                 else if(line.charAt(0) == 'C'){
                    int x = getReg(line.charAt(1));
                    char c = (char) (x % 256);
                    out.print(c);
                } else if(line.charAt(0) == 'J'){
                    i = labels.get(line.substring(1));
                } else if(line.charAt(0) == 'Z'){
                    int reg = getReg(line.charAt(1));
                    if(reg == 0)
                        i = labels.get(line.substring(2));
                } else if(line.charAt(0) == 'G') {
                    int regFirst = getReg(line.charAt(1));
                    int regSecond = getReg(line.charAt(2));
                    if (regFirst > regSecond)
                        i = labels.get(line.substring(3));
                } else if(line.charAt(0) == 'E'){
                    int regFirst = getReg(line.charAt(1));
                    int regSecond = getReg(line.charAt(2));
                    if(regFirst == regSecond)
                        i = labels.get(line.substring(3));
                } else if("Q".equals(line)){
                    break;
                } else if(!line.startsWith(":")){
                    int x = Integer.parseInt(line);
                    put(x);
                }
            }
        }

        void loadCode(List<String> program){

            for (int i = 0; i < program.size(); i++) {
                String line = program.get(i);

                if(':' == line.charAt(0))
                    labels.put(line.substring(1), i);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("quack.in")));
        out = new PrintWriter("quack.out");

        List<String> program = new ArrayList<>();
        String inputLine;
        while ((inputLine = reader.readLine()) != null)
            program.add(inputLine);

        Interpreter quack = new Interpreter();
        quack.loadCode(program);
        quack.run(program);

        out.close();
    }
}