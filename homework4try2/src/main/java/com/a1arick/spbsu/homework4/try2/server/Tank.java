package com.a1arick.spbsu.homework4.try2.server;

public class Tank implements ServerItem {
    private double x;
    private double y;
    private double radius;
    private double angle;
    private boolean isDead = false;
    private final double maxAngle = - Math.PI / 4;
    private final double minAngle = - 3 * Math.PI / 4;

    public Tank(double radius) {
        this.radius = radius;
    }

    public double getMaxAngle() {
        return maxAngle;
    }

    public double getMinAngle() {
        return minAngle;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void kill() {
        isDead = true;
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getRadius() {
        return radius;
    }


}
