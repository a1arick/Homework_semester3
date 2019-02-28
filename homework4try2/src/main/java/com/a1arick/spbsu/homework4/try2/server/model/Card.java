package com.a1arick.spbsu.homework4.try2.server.model;

import java.util.TreeSet;

public class Card {
    public TreeSet<Point> points = new TreeSet<>();

    public Card() {
        points.add(new Point(100,100));
        points.add(new Point(200,200));
        points.add(new Point(300,100));
        points.add(new Point(400,100));
        points.add(new Point(500,100));
        points.add(new Point(600,100));
    }

}
