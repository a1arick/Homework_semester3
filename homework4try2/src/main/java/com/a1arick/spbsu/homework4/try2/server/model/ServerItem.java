package com.a1arick.spbsu.homework4.try2.server.model;

public interface ServerItem {
    double getX();
    double getY();
    double getRadius();
    double getAngle();
    boolean isDead();
    Type getType();
    ShotType getShotType();

    int getClientId();
}
