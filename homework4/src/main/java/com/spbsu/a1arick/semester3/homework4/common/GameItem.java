package com.spbsu.a1arick.semester3.homework4.common;

public interface GameItem {
    int getX();
    int getY();
    double getCannonAngle();
    Type getType();

    boolean isDestroyed();

    enum Type {
        SHOT,
        TANK,
    }
}
