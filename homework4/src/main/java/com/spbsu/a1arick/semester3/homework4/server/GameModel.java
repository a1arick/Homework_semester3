package com.spbsu.a1arick.semester3.homework4.server;

import com.spbsu.a1arick.semester3.homework4.common.ShotType;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GameModel {
    private static final int dX = 10;
    private static final int dY = 10;

    private final List<Point> points;
    private final Set<ServerGameItem> shots = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<Integer, ServerGameItem> tanks = new ConcurrentHashMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public GameModel(List<Point> points) {
        this.points = points;
    }

    synchronized public void move(int clientId, boolean left) {
        try {
            ServerGameItem tank = tanks.get(clientId);
            if (tank != null && !tank.isDestroyed()) {
                int x = tank.getX() + dX; // todo учитывать угол!
                int y = tank.getY() + dY;
                tank.setX(x);
                tank.setY(y);
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void cannonMove(int clientId, boolean left) {

    }

    public void makeShot(int clientId, ShotType shotType) {

    }

    public void addTank(int clientId) {

    }

    synchronized public void update() {
        try {
            long now = System.currentTimeMillis();
            for (ServerGameItem shot : shots) {
                // todo вычислить новые координаты
                shot.setX(...);
                shot.setY(...);

                for (ServerGameItem tanks : tanks.values()) {
                    // todo если пересекаются, то удалить танк isDestroyed = true
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Point> getPoints() {
        return points;
    }
}
