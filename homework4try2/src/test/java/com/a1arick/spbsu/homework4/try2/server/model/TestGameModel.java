package com.a1arick.spbsu.homework4.try2.server.model;

import java.util.TreeSet;

public class TestGameModel extends AbstractGameModel{
    private double time;

    public TestGameModel(TreeSet<Point> points) {
        super(points);
    }


    @Override
    protected double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

}
