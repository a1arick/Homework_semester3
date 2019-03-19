package com.a1arick.spbsu.homework4.try2.server.model;

import java.util.TreeSet;

/**
 * Map game
 */
public class GameMap {
    public TreeSet<Point> points = new TreeSet<>();

    public GameMap() {
        points.add(new Point(100,400));
        points.add(new Point(200,350));
        points.add(new Point(300,250));
        points.add(new Point(400,250));
        points.add(new Point(500,300));
        points.add(new Point(600,250));
        points.add(new Point(700,350));
        points.add(new Point(800,400));
        points.add(new Point(900,300));
    }
}
