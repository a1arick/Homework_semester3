package com.spbsu.a1arick.semester3.homework4.server;

import com.spbsu.a1arick.semester3.homework4.common.GameItem;

public class ServerGameItem implements GameItem {

    private Type type;
    private double time;
    private final int speed = 10;
    private int x;
    private int y;
    private int x0;
    private int y0;
    private double cannonAngle;

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public int getX0() {
        return x0;
    }

    public int getY0() {
        return y0;
    }

    public int getSpeed() {
        return speed;
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
    public double getTime() {
        return time;
    }

    @Override
    public double getCannonAngle() {
        return cannonAngle;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    public void setTime(double time) {
        this.time = time;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    void setCannonAngle(double cannonAngle) {
        this.cannonAngle = cannonAngle;
    }
}
