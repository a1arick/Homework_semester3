package com.spbsu.a1arick.semester3.homework4;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface for bots and player tanks
 */
public interface Tank {
    void update(double time);
    void draw(GraphicsContext gc);
    void checkLife(Shot shot);
    boolean isDead();
}
