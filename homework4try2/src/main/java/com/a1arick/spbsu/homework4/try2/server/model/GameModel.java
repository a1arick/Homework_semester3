package com.a1arick.spbsu.homework4.try2.server.model;

import com.a1arick.spbsu.homework4.try2.server.Point;

import java.util.TreeSet;

public class GameModel extends AbstractGameModel{


    public GameModel(TreeSet<Point> points) {
        super(points);
    }

    @Override
    protected long getTime() {
        return System.currentTimeMillis();
    }
}
