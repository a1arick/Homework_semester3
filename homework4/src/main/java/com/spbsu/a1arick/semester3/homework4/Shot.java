package com.spbsu.a1arick.semester3.homework4;

import javafx.scene.canvas.GraphicsContext;

/**
 * Shot belonging to the tank
 */
public interface Shot {

    // todo add comments
    void draw(GraphicsContext graphicsContext);
    void update(double time);
    boolean isExploded();
    double getX();
    double getY();
    double getRadius();
}