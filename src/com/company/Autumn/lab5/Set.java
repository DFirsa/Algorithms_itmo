package com.company.Autumn.lab5;

import java.io.*;

public class Set {

    static PrintWriter out;

    static int hash(int x, int tableSize){
        x = ((x >> 16) ^ x) * 0x119de1f3;
        x = ((x >> 16) ^ x) * 0x119de1f3;
        x = (x >> 16) ^ x;
        x = Math.abs(x % tableSize);
        return x;
    }

    static class ListElement {
        ListElement next;
        ListElement prev;
        int data;
    }

    static class List{
        ListElement head;
        ListElement tail;

        void insert(int value){
            ListElement a = new ListElement();
            a.data = value;
            if (exists(value)) return;
            if (tail == null){
                head = a;
                tail = a;
            }
            else{
                tail.next = a;
                a.prev =  tail;
                tail = a;
            }
        }

        void delete(int value){
            if (head == null) return;
            ListElement a;
            a = head;
            while (a != null){
                if (a.data == value){
                    if (a.next == null && a.prev == null){
                        tail = null;
                        head = null;
                    }
                    else {
                        if (a.next == null){
                            a.prev.next = null;
                            tail = a.prev;
                        }
                        else {
                            if (a.prev == null){
                                a.next.prev = null;
                                head = a.next;
                            }
                            else{
                                a.next.prev = a.prev;
                                a.prev.next = a.next;
                            }
                        }
                    }
                    return;
                }
                else {
                    a = a.next;
                }
            }
        }

        boolean exists(int value){
            ListElement a = head;
            if (head == null) return false;
            while (a != null){
                if (a.data == value) return true;
                a = a.next;
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {

        out = new PrintWriter("set.out");
        BufferedReader reader = new BufferedReader(new FileReader(new File("set.in")));
        String line;

        List[] hashTable = new List[1048576];
        String comand;
        int value, h;

        for(int i = 0; i < hashTable.length; i++) hashTable[i] = new List();

        while ((line = reader.readLine()) != null){
            String[] in = line.split(" ");
            comand = in[0];
            value = Integer.parseInt(in[1]);
            h = hash(value, hashTable.length);

            if (comand.compareTo("insert") == 0) {
                hashTable[h].insert(value);
            }

            if (comand.compareTo("delete") == 0) {
                hashTable[h].delete(value);
            }

            if (comand.compareTo("exists") == 0) {
                if (hashTable[h].exists(value)) out.println("true");
                else out.println("false");
            }
        }

        out.close();
        reader.close();
    }
}