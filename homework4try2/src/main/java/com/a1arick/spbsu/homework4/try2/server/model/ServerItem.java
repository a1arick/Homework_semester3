package com.a1arick.spbsu.homework4.try2.server.model;

/**
 * Interface for shots and tanks
 */
public interface ServerItem {
    double getX();
    double getY();
    double getRadius();
    double getAngle();
    boolean isDead();
    GameObjectType getType();
    ShotType getShotType();

    int getClientId();
}
