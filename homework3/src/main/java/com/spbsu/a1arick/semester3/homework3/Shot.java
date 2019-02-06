package com.spbsu.a1arick.semester3.homework3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot {
    private double x;
    private double dx;
    private double y;
    private double dy;
    private double radius = 20;

    private World world;

    public Shot(double x, double y, double dx, double dy) {
        this.x = x;
        this.dx = dx;
        this.y = y;
        this.dy = dy;
    }

    public void update(double time) {
        x += dx * time;
        y += dy * time;
        dy += world.getG() * time;
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(x, y, 10, 10);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Checks is it exploded according position in the world
     * @return true if it is and false otherwise
     */
    public boolean isExploded() {
        return y > world.getY(x);
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }
}