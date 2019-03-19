package com.a1arick.spbsu.homework4.try2.server.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Abstract game model
 */
public abstract class AbstractGameModel {
    public static final double G = 9.8;
    private static final double dX = 10;
    private static final double dA = -0.25;

    private final TreeSet<Point> points;
    private final Set<Shot> shots = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<Integer, Tank> tanks = new ConcurrentHashMap<>();

    public AbstractGameModel(TreeSet<Point> points) {
        this.points = points;
    }

    protected abstract double getTime();


    /**
     * Move tank
     * @param clientId client id
     * @param isRight is right
     */
    synchronized public void move(int clientId, boolean isRight) {
        Tank tank = tanks.get(clientId);
        if (tank != null && !tank.isDead()) {
            double x = tank.getX() + dX * (isRight ? 1 : -1);
            if (x > points.last().getX()) x = points.last().getX();
            else if (x < points.first().getX()) x = points.first().getX();
            double y = getY(x);
            tank.setX(x);
            tank.setY(y);
            tanks.remove(clientId);
            tanks.put(clientId, tank);
        }
    }

    /**
     * Move tank to a certain point
     * (for  GameModelTest)
     * @param clientId client id
     * @param x certain point
     */
    synchronized public void moveOnX(int clientId, double x) {
        Tank tank = tanks.get(clientId);
        if (tank != null && !tank.isDead()) {
            double y = getY(x);
            tank.setX(x);
            tank.setY(y);
            tanks.remove(clientId);
            tanks.put(clientId, tank);
        }
    }

    /**
     * Return y coordinate for right move tank by map
     * @param x x coordinate
     * @return y coordinates depending on x coordinate
     */
    private double getY(double x) {
        Point point = new Point(x, 0);
        Point floor = points.floor(point);
        Point ceiling = points.ceiling(point);

        if (floor.equals(ceiling)) return floor.getY();
        else {
            double x1 = floor.getX();
            double y1 = floor.getY();
            double x2 = ceiling.getX();
            double y2 = ceiling.getY();
            return ((x - x1) * (y2 - y1)) / (x2 - x1) + y1;
        }
    }

    /**
     * Move cannon
     * @param clientId client id
     * @param isRight is right
     */
    public synchronized void cannonMove(int clientId, boolean isRight) {
        Tank tank = tanks.get(clientId);
        if (tank != null && !tank.isDead()) {
            double angle = tank.getAngle() + dA * (isRight ? 1 : -1);
            if (angle > tank.getMaxAngle()) angle = tank.getMaxAngle();
            if (angle < tank.getMinAngle()) angle = tank.getMinAngle();
            tank.setAngle(angle);
            tanks.remove(clientId);
            tanks.put(clientId, tank);
        }
    }

    /**
     * Move cannon to a certain angle
     * (for  GameModelTest)
     * @param clientId client id
     * @param angle certain angle
     */
    public synchronized void cannonMoveOnAngle(int clientId, double angle) {
        Tank tank = tanks.get(clientId);
        if (tank != null && !tank.isDead()) {
            tank.setAngle(angle);
            tanks.remove(clientId);
            tanks.put(clientId, tank);
        }
    }

    /**
     * Tank make shot
     * @param clientId client id
     * @param type shot type
     */
    public synchronized void makeShot(int clientId, ShotType type) {
        Tank tank = Objects.requireNonNull(tanks.get(clientId));
        if (getTime() - tank.getLastFire() < 1000 && getTime() != 0) return;
        tank.setLastFire(getTime());
        Shot shot = new Shot(type, tank);
        shot.setX0(tank.getX());
        shot.setY0(tank.getY());
        shot.setX(shot.getX0());
        shot.setY(shot.getY0());
        shot.setTime(getTime());
        shot.setAngle(tank.getAngle());
        shots.add(shot);
    }

    /**
     * Add tank on map
     * @param clientId id tank
     */
    public synchronized void addTank(int clientId) {
        Point first = points.first();
        Tank tank = new Tank(clientId);
        tank.setX(first.getX());
        tank.setY(first.getY());
        tank.setAngle(-3 * Math.PI / 4);
        tanks.put(clientId, tank);
    }

    /**
     * calculating the coordinates of the parabolic shot
     * @param shot shot
     * @param now time
     * @return new coordinate shot
     */
    private Point shotNewXY(Shot shot, double now) {
        double v0 = shot.getSpeed();
        double x0 = shot.getX0();
        double y0 = shot.getY0();
        double sin = -Math.sin(shot.getAngle());
        double cos = Math.cos(shot.getAngle());
        double deltaT;
        if (shot.getShotType() == ShotType.BULLET) deltaT = 0.01 * (now - shot.getTime());
        else deltaT = 0.005 * (now - shot.getTime());
        //double deltaT = now - shot.getTime();
        double newX = x0 + v0 * deltaT * cos;
        double newY = y0 - v0 * deltaT * sin + 0.5 * G * deltaT * deltaT;
        return new Point(newX, newY);
    }

    /**
     * Update position tanks and shots. Kills tanks if tank catch shot. Kills shots if shot flew off the map
     * @return alive shots and tanks
     */
    public synchronized List<ServerItem> update() {
        double now = getTime();
        List<Shot> deniedShots = new ArrayList<>();
        List<ServerItem> serverItems = new ArrayList<>();
        for (Shot shot : shots) {
            Point newCord = shotNewXY(shot, now);
            shot.setX(newCord.getX());
            shot.setY(newCord.getY());
            Point temp = new Point(shot.getX(), shot.getY());
            Point floor = points.floor(temp);
            Point ceiling = points.ceiling(temp);
            if (shot.getX() < points.first().getX() || shot.getX() > points.last().getX()) {
                deniedShots.add(shot);
                continue;
            }
            double x1 = floor.getX();
            double y1 = floor.getY();
            double x2 = ceiling.getX();
            double y2 = ceiling.getY();
            double left;
            double right;
            if (y1 < y2) {
                right = (temp.getY() - y1) / (y2 - y1);
                left = (temp.getX() - x1) / (x2 - x1);
            } else if (y1 > y2) {
                left = (temp.getY() - y1) / (y2 - y1);
                right = (temp.getX() - x1) / (x2 - x1);
            } else {
                left = y2;
                right = temp.getY();
            }
            if (right > left) { // the bullet went beyond the map
                for (Tank tank : tanks.values()) {
                    if (!tank.isDead() && !shot.getTank().equals(tank)) {
                        double dist = Math.sqrt((tank.getX() - shot.getX()) * (tank.getX() - shot.getX()) + (tank.getY() - shot.getY()) * (tank.getY() - shot.getY()));
                        //dist = dist / 2;
                        if (dist < shot.getRadius()) {
                            tank.kill();
                        }
                    }
                }
                shot.kill();
                deniedShots.add(shot);
            }

        }
        for (Shot shot : deniedShots) {
            shots.remove(shot);
        }
        serverItems.addAll(tanks.values());
        serverItems.addAll(shots);
        return serverItems;
    }
}
