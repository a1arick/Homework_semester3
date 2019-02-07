package com.spbsu.a1arick.semester3.homework3;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {

    private Collection<Tank> tanks;
    private Collection<Shot> shots;

    private final double leftBound;
    private final double rightBound;

    private final int width;
    private final int height;

    private List<Point2D> floor;

    private final double acceleration = 50.0;

    public World(int width, int height) {
        this.width = width;
        this.height = height;

        this.leftBound = 0.0;
        this.rightBound = (double) width;
        floor = new LinkedList<>();
        floor.add(new Point2D(0, 300));
        int xCurrent = 0;
        int yCurrent = 300;
        for (int i = 0; i < 8; i++) {
            yCurrent = yCurrent - (int)(Math.random() * 50);
            xCurrent = xCurrent + (int)(Math.random() * 200);
            floor.add(new Point2D(xCurrent, yCurrent));
        }
        floor.add(new Point2D(width, 200));

        tanks = new CopyOnWriteArrayList<>();
        shots = new CopyOnWriteArrayList<>();
    }

    public void addTank(Tank tank) {
        tanks.add(tank);
        tank.setWorld(this);
    }

    public void addShot(Shot shot) {
        shots.add(shot);
        shot.setWorld(this);
    }

    private void updateShots(double time) {
        for (Shot shot: shots) {
            shot.update(time);
            if (shot.isExploded()) {
                for (Tank tank: tanks) {
                    tank.checkLife(shot);
                }
                shots.remove(shot);
            }
        }
    }

    private void updateTanks(double time) {
        for (Tank tank: tanks) {
            tank.update(time);
            if (tank.isDead()) {
                tanks.remove(tank);
            }
        }
    }

    public void update(double time) {
        updateShots(time);
        updateTanks(time);
    }

    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, width, height);

        gc.setStroke(Color.BLACK);
        floor.stream().reduce((p1, p2) -> {gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY()); return p2;});

        for (Tank tank: tanks) {
            tank.draw(gc);
        }

        for (Shot shot: shots) {
            shot.draw(gc);
        }
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getG() {
        return 10 * getAcceleration();
    }

    public double getLeftBound() {
        return leftBound;
    }

    public double getRightBound() {
        return rightBound;
    }

    public double getY(double x) {
        Iterator<Point2D> i = floor.iterator();
        Point2D cur = i.next();
        Point2D prev = cur;
        while (i.hasNext() && cur.getX() < x) {
            prev = cur;
            cur = i.next();
        }
        double k = (cur.getY() - prev.getY()) / (cur.getX() - prev.getX());
        double b = cur.getY() - k * cur.getX();
        return k * x + b;
    }

}
