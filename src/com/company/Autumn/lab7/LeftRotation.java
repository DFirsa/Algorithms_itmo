package com.company.Autumn.lab7;

import java.io.*;
import java.util.StringTokenizer;

public class LeftRotation {

    static FastScanner in;
    static PrintWriter out;
    static int iterator = -1;

    static class TreeElement{
        int value;
        int height;
        TreeElement leftSon, rightSon, parent;
        int numDescription;
        int balance;

        public TreeElement(int value){
            this.value = value;
        }
    }

    static class Tree{
        TreeElement head;
        int[][] treeDescription;

        public Tree(int count){
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
                node.leftSon.parent = node;
                node.leftSon.height = height + 1;
            }

            if (numRight != -1){
                int thisValue = treeDescription[numRight][0];
                node.rightSon = new TreeElement(thisValue);
                node.rightSon.parent = node;
                node.rightSon.height = height + 1;
            }

            preorderAdd(node.leftSon, numLeft);
            preorderAdd(node.rightSon, numRight);
        }

        void createTree(){
            head = new TreeElement(treeDescription[0][0]);
            head.height = 1;
            preorderAdd(head, 0);
            getBalanceValue(head);
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

            node.balance = right - left;

            if (left > right)return left;
            else return right;
        }

        void smallLeftRotation(TreeElement node){
            if(node == head){
                TreeElement lSon;
                if (node.rightSon.leftSon == null)lSon = null;
                else lSon = node.rightSon.leftSon;
                node.rightSon.leftSon = node;
                node.parent = node.rightSon;
                node.rightSon = lSon;
                if (lSon != null)node.rightSon.parent = node;
                head = node.parent;
            }
            else{
                TreeElement parent = node.parent;
                TreeElement lSon;
                if (node.rightSon.leftSon != null)lSon = node.rightSon.leftSon;
                else lSon = null;
                node.rightSon.leftSon = node;
                node.parent = node.rightSon;
                node.rightSon = lSon;
                if (lSon != null)node.rightSon.parent = node;
                node.parent.parent = parent;
                if(parent.rightSon == node)parent.rightSon = node.parent;
                else parent.leftSon = node.parent;
            }
        }

        void smallRightRotation(TreeElement node){
            if (node == head){
                TreeElement rSon;
                if (node.leftSon.rightSon != null)rSon = node.leftSon.rightSon;
                else rSon = null;
                node.leftSon.rightSon = node;
                node.parent = node.leftSon;
                node.leftSon = rSon;
                if (rSon != null) node.leftSon.parent = node;
                head = node.parent;
            }
            else{
                TreeElement parent = node.parent;
                TreeElement rSon;
                if (node.leftSon.rightSon != null)rSon = node.leftSon.rightSon;
                else rSon = null;
                node.leftSon.rightSon = node;
                node.parent = node.leftSon;
                node.leftSon = rSon;
                if (rSon != null) node.leftSon.parent = node;
                node.parent.parent = parent;
                if(parent.rightSon == node)parent.rightSon = node.parent;
                else parent.leftSon = node.parent;
            }
        }

        void bigLeftRotation(){
            smallRightRotation(head.rightSon);
            smallLeftRotation(head);
        }

        void getTableNum(TreeElement node){
            if (node == null)return;
            iterator++;
            node.numDescription = iterator;
            getTableNum(node.leftSon);
            getTableNum(node.rightSon);
        }

        void writeToTable(TreeElement node, int[][] table){
            int i = node.numDescription;
            table[i][0] = node.value;
            if (node.leftSon != null){
                table[i][1] = node.leftSon.numDescription + 1;
                writeToTable(node.leftSon, table);
            }
            else table[i][1] = 0;

            if (node.rightSon != null){
                table[i][2] = node.rightSon.numDescription + 1;
                writeToTable(node.rightSon, table);
            }
            else table[i][2] = 0;
        }

        void printResult(int count){
            int[][] result = new int[count][3];
            getTableNum(head);

            writeToTable(head, result);

            out.println(count);
            for (int i = 0; i < count; i++){
                out.println(result[i][0]+" "+result[i][1]+" "+result[i][2]);
            }
        }
    }

    public static void main(String[] args){
        setup("rotation.in","rotation.out");

        int count = in.nextInt();

        Tree tree = new Tree(count);
        tree.createTree();

        tree.printResult(count);

        if (tree.head.rightSon == null){
            tree.smallLeftRotation(tree.head);
        }
        else{
            if (tree.head.rightSon.balance == -1) tree.bigLeftRotation();
            else tree.smallLeftRotation(tree.head);
        }

        tree.printResult(count);

        close();
    }

    public static void setup(String inFile, String outFile) {
        try {
            in = new LeftRotation.FastScanner(new File(inFile));
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