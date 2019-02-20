package com.a1arick.spbsu.homework4.try2.server;

public interface ServerItem {
    int getX();
    int getY();
    int getRadius();
    double getAngle();
    boolean isDead();


    void setX(int x);
    void setY(int y);
    void setRadius(int radius);
    void setAngle(double angle);
    void kill();

}
