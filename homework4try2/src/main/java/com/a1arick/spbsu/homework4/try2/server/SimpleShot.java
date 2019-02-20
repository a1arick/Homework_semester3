package com.a1arick.spbsu.homework4.try2.server;

public class SimpleShot implements Shot{
    private int x;
    private int x0;
    private int y;
    private int y0;
    private int speed = 10;
    private int radius = 10;
    private double time;
    private double angle;
    private boolean isDead = false;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }
    @Override
    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public void kill() {
        isDead = true;
    }

    @Override
    public int getX0() {
        return x0;
    }

    @Override
    public int getY0() {
        return y0;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

}
