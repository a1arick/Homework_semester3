package com.a1arick.spbsu.homework4.try2.server;

import com.a1arick.spbsu.homework4.try2.network.*;
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
        GameModel gameModel = new GameModel(new GameMap().points);
        server.start();
        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if(object instanceof AddTank) {
                    AddTank tank = (AddTank) object;
                    if(!id.contains(tank.getClientId())) {
                        gameModel.addTank(tank.getClientId());
                        id.add(tank.getClientId());
                        System.out.println("Add tank: " +  tank.getClientId());
                    }
                }

                if(object instanceof Move) {
                    Move move = (Move) object;
                    gameModel.move(move.getClientId(), move.isRight());
                }
                if(object instanceof CannonMove) {
                    CannonMove cannonMove = (CannonMove) object;
                    gameModel.cannonMove(cannonMove.getClientId(), cannonMove.isRight());
                }

                if(object instanceof MakeShot) {
                    MakeShot makeShot = (MakeShot) object;
                    gameModel.makeShot(makeShot.getClientId(), makeShot.getType());
                }

            }
        });

        while (true) {
            server.sendToAllTCP(new Update(gameModel.update()));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main (String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new ServerGame();
    }

}
