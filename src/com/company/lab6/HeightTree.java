package com.company.lab6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HeightTree {

    static class TreeElement{
        int value;
        int height;
        TreeElement leftSon, rightSon;

        public TreeElement(int value){
            this.value = value;
        }
    }

    static class Tree{
        TreeElement head;
        int[][] treeDescription;
        int height;

        public Tree(int count, Scanner in){
            height = 0;
            treeDescription = new int[count][3];
            for (int i = 0; i < count; i++){
                treeDescription[i][0] = in.nextInt();
                treeDescription[i][1] = in.nextInt() - 1;
                treeDescription[i][2] = in.nextInt() - 1;
            }
        }

        void preorderAdd(TreeElement node, int numDescription){
            if (node == null)return;
            if (node.height > height) height = node.height;
            
            int numLeft = treeDescription[numDescription][1];
            int numRight = treeDescription[numDescription][2];
            int height = node.height;
            if (numLeft != -1){
                int thisValue = treeDescription[numLeft][0];
                node.leftSon = new TreeElement(thisValue);
                node.leftSon.height = height + 1;
            }

            if (numRight != -1){
                int thisValue = treeDescription[numRight][0];
                node.rightSon = new TreeElement(thisValue);
                node.rightSon.height = height + 1;
            }

            preorderAdd(node.leftSon, numLeft);
            preorderAdd(node.rightSon, numRight);
        }

        void createTree(){
            head = new TreeElement(treeDescription[0][0]);
            head.height = 1;
            preorderAdd(head, 0);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("height.out");
        Scanner in = new Scanner(new FileInputStream("height.in"));

        int count = in.nextInt();

        if (count == 0) out.println("0");
        else{
            Tree tree = new Tree(count, in);
            tree.createTree();
            int result = tree.height;
            out.println(result);
        }
        out.close();
        in.close();
    }
}