package com.a1arick.spbsu.homework4.try2.server;

public class Point implements Comparable<Point>{
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Point o) {
        return Integer.compare(x, o.getX());
    }
}