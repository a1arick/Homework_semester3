package com.spbsu.a1arick.semester3.homework4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Bot tank
 */
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

    public Bot(double x, Color color, World world) {
        this.x = x;
        this.color = color;
        dx = 0.0;
        this.world = world;
        lastRandom = System.currentTimeMillis();
    }
    /**
     * Update position bot tank
     * @param time time last update
     */
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
    /**
     * Draw bot tank
     * @param graphicsContext graphicsContext where gun will be drawn
     */
    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x - width / 2, y - height / 2, width, height);
    }
    /**
     * Proximity shot and tank
     * @param shot shot in world
     * @return distance from tank to shot
     */
    private double proximityShotAndTank(Shot shot) {
        return Math.abs(shot.getX() - x) + Math.abs(shot.getY() - y);
    }
    /**
     * Check life tank
     * @param shot shot in world
     */
    @Override
    public void checkLife(Shot shot) {
        if (proximityShotAndTank(shot) < shot.getRadius()) {
            isDead = true;
        }
    }
    /**
     * @return true if tank dead
     */
    @Override
    public boolean isDead() {
        return isDead;
    }
}