package com.a1arick.spbsu.homework4.try2.server;

public class Tank implements ServerItem {
    private int x;
    private int y;
    private int xEndGun;
    private int yEndGun;
    private int radius;
    private double angle;
    private boolean isDead = false;
    private int gunLength;
    private final double maxAngle = - Math.PI / 4;
    private final double minAngle = - 3 * Math.PI / 4;

    public double getMaxAngle() {
        return maxAngle;
    }

    public double getMinAngle() {
        return minAngle;
    }

    public void setGunLength(int gunLength) {
        this.gunLength = gunLength;
    }

    public int getGunLength() {
        return gunLength;
    }

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
