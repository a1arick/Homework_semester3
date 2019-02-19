package com.spbsu.a1arick.semester3.homework4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SmallShot implements Shot{

    private World world;
    private double x;
    private double dx;
    private double y;
    private double dy;
    private double radius = 10;


    public SmallShot(double x, double y, double dx, double dy, World world) {
        this.x = x;
        this.dx = dx;
        this.y = y;
        this.dy = dy;
        this.world = world;
    }

    /**
     * Draws shot
     *
     * @param graphicsContext graphicsContext where shot will be drawn
     */
    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(x, y, 5, 5);
    }

    /**
     * Updates position shot
     *
     * @param time time last update
     */
    @Override
    public void update(double time) {
        x += dx * time;
        y += dy * time;
        dy += world.getG() * time;
    }

    /**
     * Checks is it exploded according position in the world
     *
     * @return true if it is and false otherwise
     */
    @Override
    public boolean isExploded() {
        return y > world.getY(x);
    }

    /**
     * x coordinate shot
     *
     * @return position x coordinate shot
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * y coordinate shot
     *
     * @return position y coordinate shot
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * radius shot
     *
     * @return radius shot
     */
    @Override
    public double getRadius() {
        return radius;
    }
}
