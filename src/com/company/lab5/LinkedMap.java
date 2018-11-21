package com.company.lab5;
import java.io.*;
import java.util.Scanner;

public class LinkedMap {

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
        ListElement prevPut;
        ListElement nextPut;
        String key;
        String data;
    }

    static class List{
        ListElement head;
        ListElement tail;

        ListElement put(String key, String value, ListElement lastPut){
            ListElement a = new ListElement();
            a.data = value;
            a.key = key;
            if (tail == null){
                head = a;
                tail = a;
                if (lastPut == null)a.prevPut = null;
                else{
                    a.prevPut = lastPut;
                    lastPut.nextPut = a;
                }
                lastPut = a;
            }
            else{
                ListElement i;
                i = head;
                while (i != null){
                    if (i.key.compareTo(key) == 0){
                        i.data = value;
                        return lastPut;
                    }
                    i = i.next;
                }

                tail.next = a;
                a.prev =  tail;
                tail = a;
                a.prevPut = lastPut;
                lastPut.nextPut = a;
                lastPut = a;
            }
            return lastPut;
        }

        ListElement restoreOrder(ListElement a, ListElement lastPut){
            if (a.nextPut != null && a.prevPut != null){
                a.prevPut.nextPut = a.nextPut;
                a.nextPut.prevPut = a.prevPut;
            }
            else {
                if (a.nextPut != null){
                    a.nextPut.prevPut = null;

                }
                else{
                    if (a.prevPut != null){
                        a.prevPut.nextPut = null;
                        lastPut = a.prevPut;
                    }
                    else{
                        lastPut = null;
                    }
                }
            }
            return lastPut;
        }

        ListElement delete(String key, ListElement lastPut){
            ListElement a;
            a = head;
            while (a != null){
                if (a.key.compareTo(key) == 0){
                    if (a.next == null && a.prev == null){
                        tail = null;
                        head = null;
                    }
                    else{
                        if (a.next == null){
                            tail = a.prev;
                            a.prev.next = null;
                        }
                        else{

                            if (a.prev == null){
                                a.next.prev = null;
                                head = a.next;
                            }
                            else {
                                a.next.prev = a.prev;
                                a.prev.next = a.next;
                            }
                        }
                    }
                    lastPut = restoreOrder(a, lastPut);
                    return lastPut;
                }
                else {
                    a = a.next;
                }
            }
            return lastPut;
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

        void prev(String key){
            ListElement a = head;
            while (a != null){
                if (key.compareTo(a.key) == 0){
                    if (a.prevPut != null) out.println(a.prevPut.data);
                    else out.println("none");
                    return;
                }
                a = a.next;
            }
            out.println("none");
        }

        void next(String key){
            ListElement a = head;
            while (a != null){
                if (a.key.compareTo(key) == 0){
                    if (a.nextPut != null ) out.println(a.nextPut.data);
                    else out.println("none");
                    return;
                }
                a = a.next;
            }
            out.println("none");
        }
    }

    public static void main(String[] args) throws IOException {
//        Scanner in = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader(new File("linkedmap.in")));
        out = new PrintWriter("linkedmap.out");
        List[] linkedMap = new List[1 << 20];

        for (int i = 0; i < linkedMap.length; i++) linkedMap[i] = new List();

        String comand, key, value, line;
        int h;
        ListElement buffer = new ListElement();
        buffer = null;
        while ((line = reader.readLine())!= null){
            String[] in = line.split(" ");
            comand = in[0];
            key = in[1];

            h = hash( key,linkedMap.length);

            if (comand.compareTo("put") == 0){
                value = in[2];

                buffer = linkedMap[h].put(key, value, buffer);
            }

            if (comand.compareTo("delete") == 0){
                buffer = linkedMap[h].delete(key, buffer);
            }

            if (comand.compareTo("get") == 0){
                linkedMap[h].get(key);
            }

            if (comand.compareTo("prev") == 0){
                linkedMap[h].prev(key);
            }

            if (comand.compareTo("next") == 0){
                linkedMap[h].next(key);
            }
        }

        reader.close();
        out.close();
    }
}
