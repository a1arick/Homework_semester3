package com.a1arick.spbsu.homework4.try2.server;

import com.a1arick.spbsu.homework4.try2.server.model.ShotType;

import java.io.Serializable;

public class Shot implements ServerItem, Serializable {
    private ShotType type;
    private double x;
    private double y;
    private double x0;
    private double y0;
    private double angle;
    private double time;
    private boolean isDead = false;
    private Tank tank;

    public Shot(ShotType type, Tank tank) {
        this.type = type;
        this.tank = tank;
    }

    public Tank getTank() {
        return tank;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double getTime() {
        return time;
    }
    public void kill() {
        isDead = true;
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
        return type.getRadius();
    }

    @Override
    public double getAngle() {
        return angle;
    }

    public double getSpeed() {
        return type.getSpeed();
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public int getClientId() {
        return tank.getClientId();
    }
}
