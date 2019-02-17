package com.spbsu.a1arick.semester3.homework3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Collection;

/**
 * Player in the game
 */
public class Player implements Tank {

    private World world;
    private double x ;
    private double y;
    private double dx;
    private double width;
    private double height ;
    private int gunLength;
    private double angle = - Math.PI / 2;
    private double da = 0.0;
    private double maxAngle = - Math.PI / 4;
    private double minAngle = - 3 * Math.PI / 4;
    private long lastFire = System.nanoTime();
    private boolean isDead = false;

    public Player(World world) {
        positionInformation(world);
        visualInformation();
        gunInformation();
    }

    /**
     * Information about world
     * @param world world in which the tank should be
     */
    private void positionInformation(World world) {
        x = 0.0;
        y = 0.0;
        dx = 1000.0;
        this.world = world;
    }

    /**
     * Information about tank
     */
    private void visualInformation() {
        width = 15.0;
        height = 15.0;
    }

    /**
     * Information about gun
     */
    private void gunInformation() {
        gunLength = 15;
        da = 0.0;
        maxAngle = - Math.PI / 4;
        minAngle = - 3 * Math.PI / 4;
    }

    /**
     * Update position tank's body
     * @param time time last update
     */
    private void updatePositionBody(double time) {
        if (!((dx > 0 && x > world.getRightBorder()) || (dx < 0 && x < world.getLeftBorder()))) {
            x += dx * time;
        }
        y = world.getY(x);
    }
    /**
     * Update position tank's gun
     * @param time time last update
     */
    private void updatePositionGun (double time) {
        if (!((da > 0 && angle > maxAngle) || (da < 0 && angle < minAngle))) {
            angle += da * time;
        }
    }
    /**
     * Update position tank's body and gun
     * @param time time last update
     */
    @Override
    public void update(double time) {
        if (isDead)
            return;
        updatePositionBody(time);
        updatePositionGun(time);
    }
    /**
     * Draw tank's body
     * @param graphicsContext graphicsContext where body will be drawn
     */
    private void drawBody(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(x - width / 2, y - height / 2, width, height);
    }

    /**
     * Draw tank's gun
     * @param graphicsContext graphicsContext where gun will be drawn
     */
    private void drawGun(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(Color.RED);
        graphicsContext.strokeLine(x, y, x + Math.cos(angle) * gunLength, y + Math.sin(angle) * gunLength);
    }
    /**
     * Draw tank's body and gun
     * @param graphicsContext graphicsContext where gun will be drawn
     */
    @Override
    public void draw(GraphicsContext graphicsContext) {
        if (isDead)
            return;
        drawBody(graphicsContext);
        drawGun(graphicsContext);
    }

    /**
     * Proximity shot and tank
     * @param shot shot in world
     * @return distance from tank to shot
     */
    private double proximityShotAndTank(Shot shot) {
        return Math.abs(shot.getX() - x) + Math.abs(shot.getY() - y);
    }

    /**
     * Check life tank
     * @param shot shot in world
     */
    @Override
    public void checkLife(Shot shot) {
        if (isDead)
            return;

        if (proximityShotAndTank(shot) < shot.getRadius()) {
            isDead = true;
        }
    }
    /**
     * Updates tank's body according pressed buttons
     * @param codes collection of pressed buttons
     */
    private void controlBody(Collection<String> codes) {
        if (codes.contains("LEFT")) {
            dx = -2 * world.getAcceleration();
        }
        else {
            if (codes.contains("RIGHT")) {
                dx = 2 * world.getAcceleration();
            } else {
                dx = 0 * world.getAcceleration();
            }
        }
    }
    /**
     * Updates tank's gun according pressed buttons
     * @param codes collection of pressed buttons
     */
    private void controlGun(Collection<String> codes) {
        if (codes.contains("UP")) {
            da = -4.0;
        } else {
            if (codes.contains("DOWN")) {
                da = 4.0;
            } else {
                da = 0.0;
            }
        }
    }
    /**
     * Updates tank's body and gun according pressed buttons
     * @param codes collection of pressed buttons
     */
    public void control(Collection<String> codes) {
        if (isDead)
            return;

        controlBody(codes);
        controlGun(codes);


        if (codes.contains("SPACE")) {
            fire();
        }
    }
    /**
     * Create shot and fire it according current gun's position and angle
     */
    private void fire() {
        if (isDead)
            return;

        if (System.nanoTime() - lastFire < 1000000000) {
            return;
        }
        int power = 6;
        world.addShot(new Shot(x, y, Math.cos(angle) * power * world.getAcceleration(), Math.sin(angle) * power * world.getAcceleration(), this.world));
        lastFire = System.nanoTime();
    }

    /**
     * @return true if tank dead
     */
    @Override
    public boolean isDead() {
        return isDead;
    }
}
