package com.spbsu.a1arick.semester3.homework3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot {
    private World world;
    private double x;
    private double dx;
    private double y;
    private double dy;
    private double radius = 15;


    public Shot(double x, double y, double dx, double dy, World world) {
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
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(x, y, 10, 10);
    }

    /**
     * Updates position shot
     *
     * @param time time last update
     */
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
    public boolean isExploded() {
        return y > world.getY(x);
    }

    /**
     * x coordinate shot
     *
     * @return position x coordinate shot
     */
    public double getX() {
        return x;
    }

    /**
     * y coordinate shot
     *
     * @return position y coordinate shot
     */
    public double getY() {
        return y;
    }

    /**
     * radius shot
     *
     * @return radius shot
     */
    public double getRadius() {
        return radius;
    }
}
