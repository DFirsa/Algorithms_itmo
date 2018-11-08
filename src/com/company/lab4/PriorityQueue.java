package com.company.lab4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class PriorityQueue {

    static PrintWriter out;

    static int push(int[] queue, int x, int tail){
        queue[tail] = x;
        tail++;
        return tail;
    }

    static int extractMin(int[] queue,int[] lines, int head, int tail){
        int min = tail - 1;
        for(int i = head; i < tail; i++) if (queue[i] < queue[min]) min = i;

        swap(queue, head, min);
        swap(lines, head, min);


        if (tail - head > 0){
            out.println(queue[head]);
            head++;
        }
        else out.println("*");
        return head;
    }

    static void decreaseKey(int[] queue, int[] lines, int x, int y, int tail, int head){
        for (int i = head; i < tail; i++){
            if (lines[i] == x) queue[i] = y;
        }
    }

    static void swap(int[] arr, int a, int b){
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

//    static Random rnd = new Random();

    public static void main(String[] args)throws FileNotFoundException{
        Scanner in = new Scanner(new FileInputStream("priorityqueue.in"));
        out = new PrintWriter("priorityqueue.out");
//        Scanner in  = new Scanner(System.in);

        int[] queue = new int[(int)Math.pow(10,6)];
        int[] numLines = new int[(int)Math.pow(10,6)];

        int head = 0;
        int tail = 0;
        String command;
        int x,y;
        int i = 1;

        while (in.hasNext()){
            command = in.next();

            if (command.compareTo("push") == 0){
//                System.out.println("tail = "+ tail);
                x = in.nextInt();
                tail = push(queue,x,tail);
                push(numLines, i,tail - 1);
                i++;
            }

            if (command.compareTo("extract-min") == 0){
                head = extractMin(queue, numLines, head, tail);
                i++;
            }

            if (command.compareTo("decrease-key") == 0){
                x = in.nextInt();
                y = in.nextInt();
                decreaseKey(queue, numLines, x, y, tail, head);
                i++;
            }
        }

        in.close();
        out.close();
    }
}
