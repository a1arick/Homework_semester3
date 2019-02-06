package com.spbsu.a1arick.semester3.homework3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Collection;

public class Player implements Tank {

    private World world;
    private double x ;
    private double y;
    private double dx;
    private double width;
    private double height ;
    private int gunLength;
    private double angel = - Math.PI / 2;
    private double da = 0.0;
    private double maxAngel = - Math.PI / 4;
    private double minAngel = - 3 * Math.PI / 4;
    private long lastFire = System.nanoTime();
    private boolean isDead = false;

    public Player() {
        positionInfo();
        visualInfo();
        gunInfo();
    }

    private void positionInfo() {
        x = 0.0;
        y = 0.0;
        dx = 1000.0;
    }

    private void visualInfo() {
        width = 15.0;
        height = 15.0;
    }

    private void gunInfo() {
        gunLength = 15;
        da = 0.0;
        maxAngel = - Math.PI / 4;
        minAngel = - 3 * Math.PI / 4;
    }

    private void updatePositionBody(double time) {
        if (!((dx > 0 && x > world.getRightBound()) || (dx < 0 && x < world.getLeftBound()))) {
            x += dx * time;
        }
        y = world.getY(x);
    }

    private void updatePositionGun (double time) {
        if (!((da > 0 && angel > maxAngel) || (da < 0 && angel < minAngel))) {
            angel += da * time;
        }
    }

    private void drawGun(GraphicsContext gc) {
        gc.setStroke(Color.RED);
        gc.strokeLine(x, y, x + Math.cos(angel) * gunLength, y + Math.sin(angel) * gunLength);
    }

    private void drawBody(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(x - width / 2, y - height / 2, width, height);
    }



    @Override
    public void update(double time) {
        if (isDead)
            return;
        updatePositionBody(time);
        updatePositionGun(time);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (isDead)
            return;
        drawBody(gc);
        drawGun(gc);
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
        if (isDead)
            return;

        if (proximityShotAndTank(shot) < shot.getRadius()) {
            isDead = true;
        }
    }

    private void controlBody(Collection<String> codes) {
        if (codes.contains("LEFT")) {
            dx = -2 * world.getAcceleration();
        }
        else {
            if (codes.contains("RIGHT")) {
                dx = 2 * world.getAcceleration();
            } else {
                dx = 0 * world.getAcceleration();
            }
        }
    }

    private void controlGun(Collection<String> codes) {
        if (codes.contains("UP")) {
            da = -4.0;
        } else {
            if (codes.contains("DOWN")) {
                da = 4.0;
            } else {
                da = 0.0;
            }
        }
    }

    public void control(Collection<String> codes) {
        if (isDead)
            return;

        controlBody(codes);
        controlGun(codes);


        if (codes.contains("SPACE")) {
            fire();
        }
    }

    private void fire() {
        if (isDead)
            return;

        if (System.nanoTime() - lastFire < 1000000000) {
            return;
        }

        int power = 6;
        Shot shot = new Shot(x, y, Math.cos(angel) * power * world.getAcceleration(), Math.sin(angel) * power * world.getAcceleration());
        lastFire = System.nanoTime();
        world.addShot(shot);
    }

    @Override
    public boolean isDead() {
        return isDead;
    }
}
