package com.company.lab5;

import java.io.*;
import java.util.Scanner;

public class Map {

    static PrintWriter out;

    static int hash(String s, int tableSize){
        int str = 0;
        for (int i = 0; i < s.length(); i++){
            str = 31 * str + s.charAt(i);
        }
        str = Math.abs(str % tableSize);
        return str;
    }

    static class ListElement {
        ListElement next;
        ListElement prev;
        String key;
        String data;
    }

    static class List{
        ListElement head;
        ListElement tail;

        void put(String key, String value){
            ListElement a = new ListElement();
            a.data = value;
            a.key = key;
            if (tail == null){
                head = a;
                tail = a;
            }
            else{
                ListElement i;
                i = head;
                while (i != null){
                    if (i.key.compareTo(key) == 0){
                        i.data = value;
                        return;
                    }
                    i = i.next;
                }

                tail.next = a;
                a.prev =  tail;
                tail = a;
            }
        }

        void delete(String key){
            ListElement a;
            a = head;
            while (a != null){
                if (a.key.compareTo(key) == 0){
                    if (a.next == null && a.prev == null){
                        tail = null;
                        head = null;
                        return;
                    }
                    else{
                        if (a.next == null){
                            tail = a.prev;
                            a.prev.next = null;
                            return;
                        }
                        else{

                            if (a.prev == null){
                                a.next.prev = null;
                                head = a.next;
                                return;
                            }
                            else {
                                a.next.prev = a.prev;
                                a.prev.next = a.next;
                                return;
                            }
                        }
                    }
                }
                else {
                    a = a.next;
                }
            }
        }

        void get(String key){
            ListElement a = head;
            while (a != null){
                if (key.compareTo(a.key) == 0) {
                    out.println(a.data);
                    return;
                }
                a = a.next;
            }
            out.println("none");
        }

//        void print(){
//            ListElement i = head;
//            while (i != null) {
//                System.out.print(i.data + " ");
//                i = i.next;
//            }
//            System.out.println();
//        }
    }

    public static void main(String[] args) throws IOException {

//        Scanner in = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader(new File("map.in")));
        out = new PrintWriter("map.out");

        List[] map = new List[1 << 21];
        for (int i = 0; i < map.length; i++) map[i] = new List();

        String comand, key, value, line;

        int h;

        while ((line = reader.readLine()) != null){
            String[] in = line.split(" ");
            comand = in[0];
            key = in[1];
            h = hash(key, map.length);

            if (comand.compareTo("put") == 0){
                value = in[2];
                map[h].put(key, value);
            }

            if (comand.compareTo("get") == 0) map[h].get(key);

            if (comand.compareTo("delete") == 0) map[h].delete(key);

//            if (comand.compareTo("end") == 0) break;
//
//            if (comand.compareTo("print") == 0) map[h].print();
        }

        reader.close();
        out.close();
    }
}