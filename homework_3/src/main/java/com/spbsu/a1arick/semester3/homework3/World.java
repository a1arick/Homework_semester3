package com.spbsu.a1arick.semester3.homework3;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The world on which the game is going
 */
public class World {

    private Collection<Tank> tanks;
    private Collection<Shot> shots;
    private List<Point2D> land;
    private final int width;
    private final int height;
    private final double acceleration = 50.0;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        land = new ArrayList<>();
        land.add(new Point2D(0, 300));
        int xCurrent = 0;
        int yCurrent = 300;
        for (int i = 0; i < 8; i++) {
            yCurrent = yCurrent - (int) (Math.random() * 50);
            xCurrent = xCurrent + (int) (Math.random() * 200);
            land.add(new Point2D(xCurrent, yCurrent));
        }
        land.add(new Point2D(width, 200));

        tanks = new CopyOnWriteArrayList<>();
        shots = new CopyOnWriteArrayList<>();
    }

    /**
     * Add tank in the world
     *
     * @param tank tank which add
     */
    public void addTank(Tank tank) {
        tanks.add(tank);
    }

    /**
     * Add shot in the world
     *
     * @param shot shot which add
     */
    public void addShot(Shot shot) {
        shots.add(shot);
    }

    /**
     * Update shots in world
     *
     * @param time time last update
     */
    private void updateShots(double time) {
        for (Shot shot : shots) {
            shot.update(time);
            if (shot.isExploded()) {
                for (Tank tank : tanks) {
                    tank.checkLife(shot);
                }
                shots.remove(shot);
            }
        }
    }

    /**
     * Update tanks in world
     *
     * @param time time last update
     */
    private void updateTanks(double time) {
        for (Tank tank : tanks) {
            tank.update(time);
            if (tank.isDead()) {
                tanks.remove(tank);
            }
        }
    }

    /**
     * Update shots and tanks in world
     *
     * @param time time last update
     */
    public void update(double time) {
        updateShots(time);
        updateTanks(time);
    }

    /**
     * Draw shots and tanks in world
     *
     * @param graphicsContext graphicsContext where tank will be drawn
     */
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(0, 0, width, height);

        graphicsContext.setStroke(Color.BROWN);
        land.stream().reduce((p1, p2) -> {
            graphicsContext.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            return p2;
        });

        for (Tank tank : tanks) {
            tank.draw(graphicsContext);
        }

        for (Shot shot : shots) {
            shot.draw(graphicsContext);
        }
    }

    /**
     * Second world coordinate
     *
     * @param x first coordinate
     * @return second coordinate
     */
    public double getY(double x) {
        Iterator<Point2D> i = land.iterator();
        Point2D cur = i.next();
        Point2D prev = cur;
        while (i.hasNext() && cur.getX() < x) {
            prev = cur;
            cur = i.next();
        }

        double angularCoefficient = (cur.getY() - prev.getY()) / (cur.getX() - prev.getX());
        double freeMember = cur.getY() - angularCoefficient * cur.getX();
        return angularCoefficient * x + freeMember;
    }

    /**
     * Get world's acceleration
     *
     * @return acceleration
     */
    public double getAcceleration() {
        return acceleration;
    }

    public double getG() {
        return 10 * getAcceleration();
    }

    public double getLeftBorder() {
        return 0.0;
    }

    public double getRightBorder() {
        return (double) width;
    }

}
