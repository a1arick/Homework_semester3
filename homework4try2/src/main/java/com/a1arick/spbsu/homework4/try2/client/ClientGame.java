package com.a1arick.spbsu.homework4.try2.client;

import com.a1arick.spbsu.homework4.try2.network.AddTank;
import com.a1arick.spbsu.homework4.try2.network.Move;
import com.a1arick.spbsu.homework4.try2.network.Network;
import com.a1arick.spbsu.homework4.try2.network.Update;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class ClientGame implements Runnable {
    private final Client client;
    private volatile boolean stopped = false;

    public ClientGame() throws IOException {
        client = new Client();
        client.start();


        Network.register(client);
        client.connect(5000, "localhost", Network.serverPort);

        client.addListener(new Listener(){

            @Override
            public void disconnected(Connection connection) {
                stopped = true;
            }

            public void received (Connection connection, Object object) {
                if(object instanceof Update) {
                    Update update = (Update) object;
                    System.out.println(update.getServerItems().toString());
                }
            }
        });


    }

    @Override
    public void run() {
        AddTank tank = new AddTank(ThreadLocalRandom.current().nextInt());
        client.sendTCP(tank);

        while (!stopped) {
            client.sendTCP(new Move(tank.getClientId(), true));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Log.error("sleep error", e);
                break;
            }
        }
    }




    public static void main (String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        ClientGame clientGame = new ClientGame();
        clientGame.run();
    }
}