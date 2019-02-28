package com.a1arick.spbsu.homework4.try2.server;

import com.a1arick.spbsu.homework4.try2.network.AddTank;
import com.a1arick.spbsu.homework4.try2.network.Move;
import com.a1arick.spbsu.homework4.try2.network.Network;
import com.a1arick.spbsu.homework4.try2.network.Update;
import com.a1arick.spbsu.homework4.try2.server.model.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.util.*;

public class ServerGame {
    Server server;

    public ServerGame() throws IOException {
        server = new Server();
        Network.register(server);
        Set<Integer> id = new HashSet<>();
        server.bind(Network.serverPort);
        //GameModel gameModel = new GameModel(getPoints());
        GameModel gameModel = new GameModel(new Card().points);
        server.start();
        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if(object instanceof AddTank) {
                    AddTank tank = (AddTank) object;
                    if(!id.contains(tank.getClientId())) {
                        gameModel.addTank(tank.getClientId());
                        id.add(tank.getClientId());
                        System.out.println("Add tank: " +  tank.getClientId());
                        //connection.sendTCP( new Card(getPoints()));
                    }
                }

                if(object instanceof Move) {
                    Move move = (Move) object;
                    gameModel.move(move.getClientId(), move.isRight());
                }

            }
        });

        while (true) {
            server.sendToAllTCP(new Update(gameModel.update()));
            //server.sendToAllTCP(new Card(getPoints()));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   /* private static TreeSet<Point> getPoints() {
        TreeSet<Point> points = new TreeSet<>();
        points.add(new Point(100,100));
        points.add(new Point(200,200));
        points.add(new Point(300,100));
        points.add(new Point(400,100));
        points.add(new Point(500,100));
        points.add(new Point(600,100));
        return points;
    }*/

    public static void main (String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new ServerGame();
    }

}
