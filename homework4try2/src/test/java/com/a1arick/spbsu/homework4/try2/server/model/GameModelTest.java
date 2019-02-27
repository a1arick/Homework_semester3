package com.a1arick.spbsu.homework4.try2.server.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class GameModelTest {

    private TestGameModel model;
    private double dE = 0.0005;

    @Before
    public void setUp() throws Exception {

        TreeSet<Point> points = new TreeSet<>();
        points.add(new Point(100, 100));
        points.add(new Point(200, 100));
        points.add(new Point(300, 100));
        points.add(new Point(400, 100));
        model = new TestGameModel(points);
    }

    @Test
    public void test1() {
        model.addTank(0);
        model.addTank(1);
        // повернуть пушку на 90 градусов
        model.cannonMoveOnAngle(0, - Math.PI / 2);

        model.setTime(0);
        assertEquals(2, model.update().size());
        model.makeShot(0, ShotType.BULLET);
        //assertEquals(2, model.update().size());

        model.setTime(2*ShotType.BULLET.getSpeed()/AbstractGameModel.G);
        List<ServerItem> items1 = model.update();

        ServerItem serverItem1 = items1.stream()
                .filter(i -> i.getClientId() == 1)
                .findAny()
                .orElse(null);
        if (serverItem1 == null) {
            fail();
        }
        assertFalse(serverItem1.isDead());

        model.setTime(2*ShotType.BULLET.getSpeed()/AbstractGameModel.G + dE);
        List<ServerItem> items2 = model.update();

        ServerItem serverItem2 = items2.stream()
                .filter(i -> i.getClientId() == 1)
                .findAny()
                .orElse(null);
        if (serverItem2 == null) {
            fail();
        }
        assertTrue(serverItem2.isDead());


    }
    @Test
    public void test2() {
        model.addTank(0);
        model.addTank(1);

        double tFull = 2*ShotType.BULLET.getSpeed() * Math.sin(Math.PI/4)/AbstractGameModel.G;
        double x = (ShotType.BULLET.getSpeed() * ShotType.BULLET.getSpeed() * Math.sin(Math.PI/2))/AbstractGameModel.G;


        model.moveOnX(1, x + 100);
        model.cannonMoveOnAngle(0, - Math.PI / 4);

        model.setTime(0);
        model.makeShot(0, ShotType.BULLET);

        model.setTime(tFull + dE);

        List<ServerItem> items1 = model.update();

        ServerItem serverItem1 = items1.stream()
                .filter(i -> i.getClientId() == 1)
                .findAny()
                .orElse(null);
        if (serverItem1 == null) {
            fail();
        }
        assertTrue(serverItem1.isDead());

    }

}