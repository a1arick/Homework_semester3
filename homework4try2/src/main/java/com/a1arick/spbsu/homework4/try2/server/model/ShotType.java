package com.a1arick.spbsu.homework4.try2.server.model;

public enum ShotType {
    BULLET(0, 3, 1),
    BOMB(1, 1, 10),
    ;

    private final int id;
    private final double speed;
    private final double radius;

    ShotType(int id, double speed, double radius) {
        this.id = id;
        this.speed = speed;
        this.radius = radius;
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
