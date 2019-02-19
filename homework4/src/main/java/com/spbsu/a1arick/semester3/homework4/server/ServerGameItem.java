package com.spbsu.a1arick.semester3.homework4.server;

import com.spbsu.a1arick.semester3.homework4.common.GameItem;

public class ServerGameItem implements GameItem {

    private Type type;
    private int x;
    private int y;
    private double cannonAngle;


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
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
