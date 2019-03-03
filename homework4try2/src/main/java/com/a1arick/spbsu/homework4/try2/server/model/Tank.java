package com.a1arick.spbsu.homework4.try2.server.model;

import java.io.Serializable;
import java.util.Objects;
/**
 * Tank in the game
 */
public class Tank implements ServerItem, Serializable {
    private double x;
    private double y;
    private final double radius = 20;
    private double angle;
    private boolean isDead = false;
    private final double maxAngle = - Math.PI / 4;
    private final double minAngle = - 3 * Math.PI / 4;
    private int clientId;
    private double lastFire;

    public Tank() {
    }

    public double getLastFire() {
        return lastFire;
    }

    public void setLastFire(double lastFire) {
        this.lastFire = lastFire;
    }

    public Tank(int clientId) {
        this.clientId = clientId;
    }

    public double getMaxAngle() {
        return maxAngle;
    }

    public double getMinAngle() {
        return minAngle;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

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
    public Type getType() {
        return Type.TANK;
    }

    @Override
    public ShotType getShotType() {
        return null;
    }

    @Override
    public int getClientId() {
        return  clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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
        return radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tank tank = (Tank) o;
        return clientId == tank.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }

    @Override
    public String toString() {
        return "Tank{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                ", angle=" + angle +
                ", isDead=" + isDead +
                ", maxAngle=" + maxAngle +
                ", minAngle=" + minAngle +
                ", clientId=" + clientId +
                '}';
    }
}
