package com.a1arick.spbsu.homework4.try2.server;

public class Tank implements ServerItem {
    private int x;
    private int y;
    private int xEndGun;
    private int yEndGun;
    private int radius;
    private double angle;
    private boolean isDead = false;

    public void setXEndGun(int xEndGun) {
        this.xEndGun = xEndGun;
    }

    public void setYEndGun(int yEndGun) {
        this.yEndGun = yEndGun;
    }

    public int getXEndGun() {
        return xEndGun;
    }

    public int getYEndGun() {
        return yEndGun;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
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
}
