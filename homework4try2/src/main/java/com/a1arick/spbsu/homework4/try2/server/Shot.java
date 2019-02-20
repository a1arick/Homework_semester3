package com.a1arick.spbsu.homework4.try2.server;

public interface Shot extends ServerItem{
    int getX0();
    int getY0();
    int getSpeed();
    double getTime();

    void setX0(int x0);
    void setY0(int y0);
    void setSpeed(int speed);
    void setTime(double time);
    void kill();

}
