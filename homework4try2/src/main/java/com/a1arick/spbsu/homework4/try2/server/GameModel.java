package com.a1arick.spbsu.homework4.try2.server;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameModel {
    private static final int dX = 10; // ?
    private static final int dY = 10;

    private final TreeSet<Point> points;
    private final Set<Shot> shots = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<Integer, Tank> tanks = new ConcurrentHashMap<>();

    public GameModel(TreeSet<Point> points) {
        this.points = points;
    }


    synchronized public void move(int clientId, boolean left) {
        Tank tank = tanks.get(clientId);
        if (tank != null && !tank.isDead()) {
            int x = tank.getX() + dX; // todo учитывать угол!
            int y = tank.getY() + dY;
            tank.setX(x);
            tank.setY(y);
        }
    }

    public void cannonMove(int clientId, boolean left) {

    }

    synchronized public void makeShot(int clientId, Shot shot) {
        shot.setX0((tanks.get(clientId).getXEndGun()));
        shot.setY0((tanks.get(clientId).getYEndGun()));
        shot.setX(shot.getX0());
        shot.setY(shot.getY0());
        shot.setSpeed(10);
        shot.setRadius(5);
        shot.setTime(System.nanoTime());

        shots.add(shot);
        // приравнять shot координаты концы пушки и записать в srtTime текущее время
    }

    public void addTank(int clientId) {
        Point first = points.first();
        Tank tank = new Tank();
        tank.setX(first.getX());
        tank.setY(first.getY());
        tank.setRadius(10);
        tank.setAngle(...);
        tank.setxEndGun(..);
        tank.setyEndGun(..);

        tanks.put(clientId, tank);
    }

    private Point shotNewXY(Shot shot, double now) {
        int v0 = shot.getSpeed();
        int x0 = shot.getX0();
        int y0 = shot.getY0();
        double sin = Math.sin(shot.getAngle());
        double cos = Math.cos(shot.getAngle());
        double deltaT = now - shot.getTime();
        double g = 10;

        int newX = (int) (x0 + v0 * deltaT * cos);
        int newY = (int) (y0 - v0 * deltaT * sin - 0.5 * g * deltaT * deltaT);


        return new Point(newX, newY);
    }


    synchronized public void update() {
        long now = System.currentTimeMillis();
        List<Shot> deniedShots = new ArrayList<>();
        List<Integer> deniedTanks = new ArrayList<>();
        for (Shot shot : shots) {
            Point newCord = shotNewXY(shot, now);
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

                for (Map.Entry<Integer, Tank> integerTankEntry : tanks.entrySet()) {
                    Tank tank = integerTankEntry.getValue();
                    int tankID = integerTankEntry.getKey();
                    double dist = Math.sqrt((tank.getX() - shot.getX()) * (tank.getX() - shot.getX()) + (tank.getY() - shot.getY()) * (tank.getY() - shot.getY()));
                    if (dist < (tank.getRadius() + shot.getRadius()) / 2) {
                        tank.kill();
                        deniedTanks.add(tankID);
                    }
                }
                for (Integer tankID : deniedTanks) {
                    tanks.remove(tankID);
                }

                if (left == right) {
                    shot.kill();
                    deniedShots.add(shot);
                }
            } else {
                shot.kill();
                deniedShots.add(shot);
            }


        }

        for (Shot shot : deniedShots) {
            shots.remove(shot);
        }
    }

}
