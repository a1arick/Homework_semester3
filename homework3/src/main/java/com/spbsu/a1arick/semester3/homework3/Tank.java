package com.spbsu.a1arick.semester3.homework3;

import javafx.scene.canvas.GraphicsContext;

public interface Tank {
    void update(double time);
    void draw(GraphicsContext gc);
    void setWorld(World world);
    void checkLife(Shot shot);
    boolean isDead();
}
