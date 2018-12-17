package com.company.lab6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CheckTree {

    static class TreeElement{
        int value, min, max;
        TreeElement leftSon, rightSon;
        boolean correct;

        public TreeElement(int value){
            this.value = value;
        }
    }

    static class Tree{
        TreeElement head;
        boolean correct;
        int[][] treeDescription;

        public Tree(int count, Scanner in){
            correct = true;
            treeDescription = new int[count][3];
            for (int i = 0; i < count; i++){
                treeDescription[i][0] = in.nextInt();
                treeDescription[i][1] = in.nextInt() - 1;
                treeDescription[i][2] = in.nextInt() - 1;
            }
        }

        void preorderAdd(TreeElement node, int numDescription){
            if (node == null)return;

            int numLeft = treeDescription[numDescription][1];
            int numRight = treeDescription[numDescription][2];
            if (numLeft != -1){
                int thisValue = treeDescription[numLeft][0];
                node.leftSon = new TreeElement(thisValue);
                node.leftSon.max = node.value;
                node.leftSon.min = node.min;
            }

            if (numRight != -1){
                int thisValue = treeDescription[numRight][0];
                node.rightSon = new TreeElement(thisValue);
                node.rightSon.min = node.value;
                node.rightSon.max = node.max;
            }

            if (node.value < node.max && node.value > node.min)node.correct = true;
            else node.correct = false;
            if (!node.correct)correct = false;

            preorderAdd(node.leftSon, numLeft);
            preorderAdd(node.rightSon, numRight);
        }

        void createTree(){
            head = new TreeElement(treeDescription[0][0]);
            head.max = (int)Math.pow(10,9)+1;
            head.min = head.max * (-1);
            preorderAdd(head, 0);
        }
    }

     public static void main(String[] args) throws FileNotFoundException {
         Scanner in = new Scanner(new FileInputStream("check.in"));
         PrintWriter out = new PrintWriter("check.out");

         int count = in.nextInt();

         if (count == 0) out.println("YES");
         else{
             Tree tree = new Tree(count, in);
             tree.createTree();
             if (tree.correct)out.println("YES");
             else out.println("NO");
         }
         
         in.close();
         out.close();
     }
}