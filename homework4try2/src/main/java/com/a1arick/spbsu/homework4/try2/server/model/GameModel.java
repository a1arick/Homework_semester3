package com.a1arick.spbsu.homework4.try2.server.model;

import com.esotericsoftware.kryonet.Connection;

import java.util.TreeSet;

public class GameModel extends AbstractGameModel {


    public GameModel(TreeSet<Point> points) {
        super(points);
    }

    @Override
    protected double getTime() {
        return System.currentTimeMillis();
    }
}
