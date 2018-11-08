package com.company.lab5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Set {

    static PrintWriter out;

    static int hash1(int n, int tableSize){
        int hash = Math.abs(n % tableSize);
        return hash;
    }

    static int hash2(int n, int tableSize){
        int hash = n*n % tableSize;
        hash = hash << (tableSize / 27);
        return hash;
    }

    static void insert(int n, String[] hashTable){
        int t;
        int h1 = hash1(n, hashTable.length);
        int h2 = hash2(n, hashTable.length);
        if (hashTable[h1].compareTo(Integer.toString(n)) != 0 && hashTable[h2].compareTo(Integer.toString(n)) != 0){
            if(hashTable[h1].compareTo("null") == 0) hashTable[h1] = Integer.toString(n);
            else{
                if(hashTable[h2].compareTo("null") != 0){
                    t = Integer.parseInt(hashTable[h2]);
                    hashTable[h2] = Integer.toString(n);
                    hashTable[hash2(t, hashTable.length)] = Integer.toString(t);
                }
                else hashTable[h2] = Integer.toString(n);
            }
        }
    }

    static void delete(int n, String[] hashTable){
        int h1 = hash1(n, hashTable.length);
        int h2 = hash2(n, hashTable.length);
        if (hashTable[h1].compareTo(Integer.toString(n)) == 0) hashTable[h1] = "null";
        else{
            if (hashTable[h2].compareTo(Integer.toString(n)) == 0) hashTable[h2] = "null";
        }
    }

    static void exists(int n, String[] hashTable){
        int h1 = hash1(n, hashTable.length);
        int h2 = hash2(n, hashTable.length);
        if(hashTable[h1].compareTo(Integer.toString(n)) == 0 || hashTable[h2].compareTo(Integer.toString(n)) == 0) out.println("true");
        else out.println("false");
    }

   /* static  String[] reHash(String hashTable){
        boolean overflow = true;
        if(overflow){
            String[] nHashTable = new String[hashTable.length()+ ( hashTable.length() / 2)];
        }
        else {
            String[] nHashTable = new String[hashTable.length()];
        }


    }*/

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream("set.in"));
        out = new PrintWriter("set.out");

        String[] hashTable = new String[1023];
        for (int i = 0; i < hashTable.length; i++){
            hashTable[i] = "null";
        }

        String comand;
        int n;

        while (in.hasNext()){
            comand = in.next();
            if(comand.compareTo("insert") == 0){
                n = in.nextInt();
                insert(n,hashTable);
            }

            if (comand.compareTo("delete") == 0){
                n = in.nextInt();
                delete(n,hashTable);
            }

            if (comand.compareTo("exists") == 0){
                n = in.nextInt();
                exists(n, hashTable);
            }
        }

        in.close();
        out.close();
    }
}
