package com.example.project;

import android.util.Pair;

public class Point implements Comparable<Point>{
    public Point(Double distanceBetween, String name) {
        Pair<Double,String> pr= new Pair<>(distanceBetween, name);
        this.setPair(pr);


    }

    public int compareTo(Point other) {
//comparison by the x axis
       return Double.compare(this.pair.first, other.pair.first);
//also possible: return this.x - other.x;
    }

    public Pair<Double, String> getPair() {
        return pair;
    }

    public void setPair(Pair<Double, String> pair) {
        this.pair = pair;
    }

    private Pair<Double, String> pair;
}