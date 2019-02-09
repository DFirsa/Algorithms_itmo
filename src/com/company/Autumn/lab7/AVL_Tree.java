package com.company.Autumn.lab7;

import java.io.*;
import java.util.StringTokenizer;

public class AVL_Tree {

    static FastScanner in;
    static PrintWriter out;

    static class TreeElement{
        int value, height, balance;
        TreeElement leftSon, rightSon, parent;

        public TreeElement(int value){
            this.value = value;
        }
    }

    static class Tree{
        TreeElement head;

        void insert(int data){
            if (exists(data))return;

            if (head == null){
                head = new TreeElement(data);
                head.height = 1;
            }
            else{
                TreeElement i = head;
                while (true){
                    if (data > i.value){
                        if (i.rightSon == null){
                            i.rightSon = new TreeElement(data);
                            i.rightSon.height = i.height + 1;
                            i.rightSon.parent = i;
                            break;
                        }
                        else i = i.rightSon;
                    }
                    else{
                        if (i.leftSon == null) {
                            i.leftSon = new TreeElement(data);
                            i.leftSon.height = i.height + 1;
                            i.leftSon.parent = i;
                            break;
                        }
                        else i = i.leftSon;
                    }
                }

                upAndRotation(i);
            }
        }

        boolean exists(int data){
            TreeElement i = head;
            while(i != null){
                if (i.value == data)return true;
                if (i.value < data){
                    i = i.rightSon;
                }
                else i = i.leftSon;
            }
            return false;
        }

        void delete(int data){

            TreeElement i = head;
            while (true){
                if (i == null)return;
                if (i.value == data) break;
                else{
                    if (i.value > data)i = i.leftSon;
                    else i = i.rightSon;
                }
            }

            if (i.leftSon == null && i.rightSon == null){
                if (i == head)head = null;
                else{
                    if (i.parent.rightSon == i) i.parent.rightSon = null;
                    else i.parent.leftSon = null;

                    i = i.parent;
                }
            }
            else {
                if (i.leftSon != null && i.rightSon != null){
                    TreeElement prev = i.leftSon;
                    while(prev.rightSon != null){
                        prev = prev.rightSon;
                    }
                    if(prev == i.leftSon && i == head){
                        prev.rightSon = head.rightSon;
                        head.rightSon.parent = prev;
                        head = prev;

                        i = prev;
                    }
                    else{
                        if (prev.leftSon == null){
                            i.value = prev.value;
                            if (prev.parent.leftSon == prev){
                                prev.parent.leftSon = null;
                                i = prev.parent;
                                prev.parent = null;
                            }
                            else{
                                prev.parent.rightSon = null;
                                i = prev.parent;
                                prev.parent = null;
                            }
                        }
                        else{
                            i.value = prev.value;
                            prev.parent.rightSon = prev.leftSon;
                            prev.leftSon.parent = prev.parent;
                            i = prev.parent;
                        }
                    }
                }
                else{
                    if(i.leftSon != null){
                        if (i == head){
                            head = i.leftSon;
                            i.leftSon.parent = null;
                        }
                        else{
                            if(i.parent.leftSon == i){
                                i.parent.leftSon = i.leftSon;
                                i.leftSon.parent = i.parent;
                            }
                            else{
                                i.parent.rightSon = i.leftSon;
                                i.leftSon.parent = i.parent;
                            }
                            i = i.parent;
                        }
                    }
                    else{
                        if(i == head){
                            head = i.rightSon;
                            i.rightSon.parent = null;
                        }
                        else{
                            if(i.parent.leftSon == i){
                                i.parent.leftSon = i.rightSon;
                                i.rightSon.parent = i.parent;
                            }
                            else{
                                i.parent.rightSon = i.rightSon;
                                i.rightSon.parent = i.parent;
                            }

                            i = i.parent;
                        }
                    }
                }
            }

            if (head != null)upAndRotation(i);
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

        void getHeight(TreeElement node){
            if (node == head)node.height = 1;
            else node.height = node.parent.height + 1;

            if (node.leftSon != null)getHeight(node.leftSon);
            if (node.rightSon != null)getHeight(node.rightSon);
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
                TreeElement rSon = null;
                if (node.leftSon.rightSon != null)rSon = node.leftSon.rightSon;
                node.leftSon.rightSon = node;
                node.parent = node.leftSon;
                node.leftSon = rSon;
                if (rSon != null) node.leftSon.parent = node;
                node.parent.parent = parent;
                if(parent.rightSon == node)parent.rightSon = node.parent;
                else parent.leftSon = node.parent;
            }
        }

        void bigLeftRotation(TreeElement node){
            smallRightRotation(node.rightSon);
            smallLeftRotation(node);
        }

        void bigRightRotation(TreeElement node) {
            smallLeftRotation(node.leftSon);
            smallRightRotation(node);
        }

        void upAndRotation(TreeElement node){
            if (node.balance > 1) {
                if (node.rightSon != null){
                    if (node.rightSon.balance == -1)bigLeftRotation(node);
                    else smallLeftRotation(node);
                }
                else smallLeftRotation(node);
                getHeight(head);
                getBalanceValue(head);
            }
            if (node.balance < -1) {
                if (node.leftSon != null){
                    if (node.leftSon.balance != 1)smallRightRotation(node);
                    else bigRightRotation(node);
                }
                else smallRightRotation(node);
                getHeight(head);
                getBalanceValue(head);
            }
            if (node != head)upAndRotation(node.parent);
            else return;
        }
    }

    public static void main(String[] args){
        setup("avlset.in","avlset.out");
        int count = in.nextInt();
        String comand;

        Tree avl = new Tree();

        for (int i = 0; i < count; i++){
            comand = in.next();
            int value = in.nextInt();

            if (comand.equals("A")){
                avl.insert(value);
                out.println(avl.head.balance);
            }

            if (comand.equals("D")){
                avl.delete(value);
                out.println(avl.head.balance);
            }

            if (comand.equals("C")){
                if (avl.exists(value))out.println("Y");
                else out.println("N");
            }
        }

        close();
    }

    public static void setup(String inFile, String outFile) {
        try {
            in = new AVL_Tree.FastScanner(new File(inFile));
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
