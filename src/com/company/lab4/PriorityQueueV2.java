package com.company.lab4;

import java.util.Scanner;

public class PriorityQueueV2 {

    static int push(int tail, int[][] arr, int n, int i){
        arr[tail][1] = n;
        arr[tail][2] = i;
        tail++;
        heapify(arr, 0 , tail );
        return tail;
    }

    static int leftSon(int i){
        int son = 2*i + 1;
        return  son;
    }

    static int rightSon(int i){
        int son = 2*i + 2;
        return son;
    }

    static void swap(int[][] a, int i, int j){
        int t1 = a[i][1];
        int t2 = a[i][2];
        a[i][1] = a[j][1];
        a[i][2] = a[j][2];
        a[j][1] = t1;
        a[j][2] = t2;
    }

    static void extractMin(int[][] arr, int i){}

    static void heapify(int[][] a, int i, int length){
        int n = length/2 - 1;
        while (i <= n){
            if(leftSon(i) == length - 1){
                if(a[i][1] > a[leftSon(i)][1]){break;}
                else{
                    swap(a, i,leftSon(i));
                    i = leftSon(i);
                }
            }
            else{
                if(a[i][1] > a[leftSon(i)][1] && a[i][1] > a[rightSon(i)][1]){break;}
                else{
                    if(a[leftSon(i)][1] > a[rightSon(i)][1]){
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
        Scanner in = new Scanner(System.in);
        int[][] queue = new int[1000000][2];
        String command;
        int n;
        int tail = 0;
        int i = 0;
        while (in.hasNext()){
            command = in.next();
            if(command.compareTo("end") == 0){break;}

            if (command.compareTo("push") == 0){
                n = in.nextInt();
                i++;
                push(tail, queue, n, i);
            }

            if (command.compareTo("extract-min") == 0){
                n = in.nextInt();
            }

        }
    }
}
