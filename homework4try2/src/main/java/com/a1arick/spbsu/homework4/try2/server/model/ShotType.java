package com.a1arick.spbsu.homework4.try2.server.model;

public enum ShotType {
    BULLET(0, 30, 10),
    BOMB(1, 1, 10),
    ;

    private int id;
    private double speed;
    private double radius;

    ShotType(int id, double speed, double radius) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public static ShotType valueOf(int id) {
        return id == 0 ? BULLET : BOMB;
    }
}
