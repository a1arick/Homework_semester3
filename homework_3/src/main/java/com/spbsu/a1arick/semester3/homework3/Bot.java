package com.spbsu.a1arick.semester3.homework3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Bot implements Tank {
    private World world;
    private double x;
    private double dx;
    private double y;

    private double width = 15.0;
    private double height = 15.0;
    private Color color;

    private boolean isDead = false;
    private long lastRandom;

    public Bot(double x, Color color) {
        this.x = x;
        this.color = color;
        dx = 0.0;
        lastRandom = System.currentTimeMillis();
    }

    @Override
    public void update(double time) {
        if ((x >= 1000 && dx > 0) || (x <= 0 && dx < 0)) {
            Random random = new Random();
            dx = (random.nextInt(3) - 1) * world.getAcceleration();
        }

        x += dx * time;
        y = world.getY(x);

        if (System.currentTimeMillis() - lastRandom < 1000) {
            return;
        }

        lastRandom = System.currentTimeMillis();
        Random random = new Random();
        dx = (random.nextInt(3) - 1) * world.getAcceleration();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    private double proximityShotAndTank(Shot shot) {
        return Math.abs(shot.getX() - x) + Math.abs(shot.getY() - y);
    }

    @Override
    public void checkLife(Shot shot) {
        if (proximityShotAndTank(shot) < shot.getRadius()) {
            isDead = true;
        }
    }

    @Override
    public boolean isDead() {
        return isDead;
    }
}