package com.company.Spring.lab3;

import java.io.*;
import java.util.*;

public class spantree {
    static FastScanner in;
    static PrintWriter out;

    static class Point{
        int x;
        int y;
        int num;

        Point(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static class Graph{

        int MAX = 100000;

        ArrayList<Point> pointsSet;
        Point[] points;
        boolean[] usedPoints;
        double[] weights;

        Graph(int countPoints){

            points = new Point[countPoints];
            usedPoints = new boolean[countPoints];
            weights = new double[countPoints];

            pointsSet = new ArrayList<>();

            for (int i = 0; i < countPoints; i++){
                weights[i] = MAX;
            }
        }

        double putWeight(Point a, Point b){
            double weight = (double)Math.sqrt( Math.pow((b.x - a.x),2) + Math.pow((b.y - a.y),2));
            return weight;
        }

        void read(int countPoints){

            for (int i = 0; i < countPoints; i++){
               points[i] = new Point(in.nextInt(), in.nextInt(), i);
            }
        }

        Point pop(){
            int min = 0;
            for (int i = 0; i < pointsSet.size(); i++){
                if (weights[pointsSet.get(i).num] < weights[pointsSet.get(min).num]) min = i;
            }

            Point result = pointsSet.get(min);
            pointsSet.remove(min);
            return result;
        }

        double getWeights(){
            weights[0] = 0;

            for (int i = 0; i < weights.length; i++) pointsSet.add(points[i]);

            while (!pointsSet.isEmpty()){
                Point v = pop();
                usedPoints[v.num] = true;

                for (int i = 0; i < weights.length; i++ ){
                    if (v.num == i)continue;
                    double weight = putWeight(v, points[i]);
                    if (!usedPoints[i] && weight < weights[i]){
                        weights[i] = weight;
                    }
                }
            }

            double result = 0;
            for (int i = 0; i < weights.length; i++)result += weights[i];

            return result;
        }
    }

    public static void main(String[] args){
        setup("spantree.in","spantree.out");
//        setup("cycle.in","cycle.out");

        int countPoints = in.nextInt();

        Graph graph = new Graph(countPoints);

        graph.read(countPoints);

        double result = graph.getWeights();

        out.print(result);

        close();
    }

    public static void setup(String inFile, String outFile) {
        try {
            in = new FastScanner(new File(inFile));
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
