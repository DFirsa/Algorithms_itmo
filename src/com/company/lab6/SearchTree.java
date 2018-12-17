package com.company.lab6;

import java.io.*;
import java.util.Scanner;

public class SearchTree {

    static PrintWriter out;

    static class TreeElement{
        int value;
        TreeElement leftSon, rightSon, parent;

        public TreeElement(int value){
            this.value = value;
        }
    }

    static class Tree{
        TreeElement head;

        void insert(int data){
            if (exists(data))return;

            if (head == null) head = new TreeElement(data);
            else{
                TreeElement i = head;
                while (true){
                    if (data > i.value){
                        if (i.rightSon == null){
                            i.rightSon = new TreeElement(data);
                            i.rightSon.parent = i;
                            break;
                        }
                        else i = i.rightSon;
                    }
                    else{
                        if (i.leftSon == null) {
                            i.leftSon = new TreeElement(data);
                            i.leftSon.parent = i;
                            break;
                        }
                        else i = i.leftSon;
                    }
                }
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
                    }
                    else{
                        if (prev.leftSon == null){
                            i.value = prev.value;
                            if (prev.parent.leftSon == prev){
                                prev.parent.leftSon = null;
                                prev.parent = null;
                            }
                            else{
                                prev.parent.rightSon = null;
                                prev.parent = null;
                            }
                        }
                        else{
                            i.value = prev.value;
                            prev.parent.rightSon = prev.leftSon;
                            prev.leftSon.parent = prev.parent;
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
                        }
                    }
                }
            }
        }

        void next(int data){
            int next = 0;
            boolean existsNext = false;

            TreeElement i = head;
            while(i != null){
                if (i.value <= data) i = i.rightSon;
                else{
                    existsNext = true;
                    next = i.value;
                    i = i.leftSon;
                }
            }

            if (existsNext)out.println(next);
            else out.println("none");
        }

        void prev(int data){
            int prev = 0;
            boolean existsPrev = false;

            TreeElement i = head;
            while(i != null){
                if (i.value >= data) i = i.leftSon;
                else{
                    existsPrev = true;
                    prev = i.value;
                    i = i.rightSon;
                }
            }

            if (existsPrev)out.println(prev);
            else out.println("none");
        }
    }

    public static void main(String[] args) throws IOException {
//        Scanner in = new Scanner(new FileInputStream("bstsimple.in"));
        BufferedReader reader = new BufferedReader(new FileReader("bstsimple.in"));
        out = new PrintWriter("bstsimple.out");
        Tree tree = new Tree();
        String command, line;
        int data;

        while((line = reader.readLine())!= null){
            String[] in = line.split(" ");
            command = in[0];
            data = Integer.parseInt(in[1]);

            if (command.equals("insert")){
                tree.insert(data);
            }

            if (command.equals("delete")){
                tree.delete(data);
            }

            if (command.equals("exists")){
                if(tree.exists(data))out.println("true");
                else out.println("false");
            }

            if (command.equals("next")){
                tree.next(data);
            }

            if (command.equals("prev")){
                tree.prev(data);
            }
        }
        out.close();
    }
}
