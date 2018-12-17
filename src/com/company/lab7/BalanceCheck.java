package com.company.lab7;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BalanceCheck {

    static FastScanner in;
    static PrintWriter out;

    static class TreeElement{
        int value;
        int height;
        TreeElement leftSon, rightSon;
        int numDescription;

        public TreeElement(int value){
            this.value = value;
        }
    }

    static class Tree{
        TreeElement head;
        int[][] treeDescription;
        int[] balanceValue;

        public Tree(int count){
            balanceValue = new int[count];
            treeDescription = new int[count][3];
            for (int i = 0; i < count; i++){
                treeDescription[i][0] = in.nextInt();
                treeDescription[i][1] = in.nextInt() - 1;
                treeDescription[i][2] = in.nextInt() - 1;
            }
        }

        void preorderAdd(TreeElement node, int numDescription){
            if (node == null)return;

            node.numDescription = numDescription;
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

        int getBalanceValue(TreeElement node){
            int left,right;
            if (node.leftSon != null){
                left = getBalanceValue(node.leftSon);
            }
            else left = node.height;

            if (node.rightSon != null){
                right = getBalanceValue(node.rightSon);
            }
            else right = node.height;

            balanceValue[node.numDescription] = right - left;

            if (left > right)return left;
            else return right;
        }

        void printBalance(){
            getBalanceValue(head);

            for (int i = 0; i < balanceValue.length; i++){
                out.println(balanceValue[i]);
            }
        }
    }

    public static void main(String[] args){
        setup("balance.in","balance.out");

        int count = in.nextInt();

        Tree tree = new Tree(count);
        tree.createTree();
        tree.printBalance();

        close();
    }

    public static void setup(String inFile, String outFile) {
        try {
            in = new BalanceCheck.FastScanner(new File(inFile));
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