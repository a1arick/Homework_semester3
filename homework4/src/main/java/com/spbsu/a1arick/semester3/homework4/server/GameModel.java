package com.spbsu.a1arick.semester3.homework4.server;

import com.spbsu.a1arick.semester3.homework4.common.ShotType;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class GameModel {
    private static final int dX = 10;
    private static final int dY = 10;

    private final TreeSet<Point> points;
    private final Set<ServerGameItem> shots = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<Integer, ServerGameItem> tanks = new ConcurrentHashMap<>();

    public GameModel(TreeSet<Point> points) {
        this.points = points;
    }

    synchronized public void move(int clientId, boolean left) {
        ServerGameItem tank = tanks.get(clientId);
        if (tank != null && !tank.isDestroyed()) {
            int x = tank.getX() + dX; // todo учитывать угол!
            int y = tank.getY() + dY;
            tank.setX(x);
            tank.setY(y);
        }
    }

    public void cannonMove(int clientId, boolean left) {

    }

    synchronized public void makeShot(int clientId, ShotType shotType) {
        ServerGameItem shot = tanks.get(clientId);
        // приравнять shot координаты концы пушки и записать в srtTime текущее время
    }

    public void addTank(int clientId) {

    }

    private Point shotNewCord(ServerGameItem shot) {
        int v0 = shot.getSpeed();
        int x0 = shot.getX0();
        int y0 = shot.getY0();
        double sin = Math.sin(shot.getCannonAngle());
        double cos = Math.cos(shot.getCannonAngle());
        double deltaT = System.nanoTime() - shot.getTime();
        double g = 10;

        int newX = (int) (x0 + v0 * deltaT * cos);
        int newY = (int) (y0 - v0 * deltaT * sin - 0.5 * g * deltaT * deltaT);


        return new Point(newX, newY);
    }


    synchronized public void update() {
        long now = System.currentTimeMillis();
        for (ServerGameItem shot : shots) {

            if (!shot.isDestroyed) {
                Point newCord = shotNewCord(shot);
                // todo вычислить новые координаты
                shot.setX(newCord.getX());
                shot.setY(newCord.getY());
                Point temp = new Point(shot.getX(), shot.getY());
                Point floor = points.floor(temp);
                Point ceiling = points.ceiling(temp);

                int x1 = floor.getX();
                int y1 = floor.getY();
                int x2 = ceiling.getX();
                int y2 = ceiling.getY();

                // учесть что floor и ceiling могут быть одинаковыми
                double left = (double) (temp.getY() - y1) / (y2 - y1);
                double right = (double) (temp.getX() - x1) / (x2 - x1);

                if (left >= right) {
                    // пуля над картой

                    for (ServerGameItem tanks : tanks.values()) {
                        // todo если пересекаются, то удалить танк isDestroyed = true
                        if (!tanks.isDestroyed) {
                            double dist = Math.sqrt((tanks.getX() - shot.getX()) * (tanks.getX() - shot.getX()) + (tanks.getY() - shot.getY()) * (tanks.getY() - shot.getY()));
                            if (dist < (tanks.getRadius() + shot.getRadius()) / 2) {
                                tanks.isDestroyed = true;
                            }
                        }
                    }

                    if (left == right) shot.isDestroyed = true;
                } else {
                    shot.isDestroyed = true;
                }
            }
        }
    }


    public TreeSet<Point> getPoints() {
        return points;
    }
}
