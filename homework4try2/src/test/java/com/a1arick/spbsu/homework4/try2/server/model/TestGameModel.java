package com.a1arick.spbsu.homework4.try2.server.model;

import com.a1arick.spbsu.homework4.try2.server.Point;
import org.junit.Test;

import java.util.TreeSet;

public class TestGameModel extends AbstractGameModel{
    private long time;

    public TestGameModel(TreeSet<Point> points) {
        super(points);
    }


    @Override
    protected long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    @Test
    public void test1() {
       addTank(0);
       addTank(1);


    }
}
