package com.a1arick.spbsu.homework4.try2.server.model;

/**
 *  Shot type in game
 */
public enum ShotType {
    BULLET(60, 30),
    BOMB(30, 50);

    private double speed;
    private double radius;

    ShotType(double speed, double radius) {
        this.speed = speed;
        this.radius = radius;
    }

    ShotType() {

    }
    public double getSpeed() {
        return speed;
    }

    public double getRadius() {
        return radius;
    }

    public static ShotType valueOf(int id) {
        return id == 0 ? BULLET : BOMB;
    }
}
