package com.a1arick.spbsu.homework4.try2.client;

import com.a1arick.spbsu.homework4.try2.network.*;
import com.a1arick.spbsu.homework4.try2.server.model.*;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Client for game
 */
public class ClientGame extends Application {

    private final Client client;
    private volatile boolean stopped = false;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 500;
    private List<Point2D> land;
    private GameMap gameMap = new GameMap();
    List<ServerItem> serverItems = new ArrayList<>();

    /**
     * Connects to the server and updates the contents of the card
     * @throws IOException IOException
     */
    public ClientGame() throws IOException {
        client = new Client();
        client.start();
        Network.register(client);
        client.connect(5000, "localhost", Network.serverPort);


        client.addListener(new Listener() {

            @Override
            public void disconnected(Connection connection) {
                stopped = true;
            }

            public void received(Connection connection, Object object) {
                if (object instanceof Update) {
                    Update update = (Update) object;
                    System.out.println(update.getServerItems().toString());
                    serverItems = update.getServerItems();
                }
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("tanks");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Collection<String> codes = new LinkedHashSet<>();

        scene.setOnKeyPressed(event -> {
            codes.add(event.getCode().toString());
        });

        scene.setOnKeyReleased(event -> {
            codes.remove(event.getCode().toString());
        });

        primaryStage.show();

        draw(graphicsContext);

        new Thread(() -> {
            AddTank tank = new AddTank(ThreadLocalRandom.current().nextInt());
            client.sendTCP(tank);
            while (!stopped) {
                graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
                try {
                    draw(graphicsContext);
                } catch (IOException e) {
                    Log.error("draw error", e);
                }
                control(codes, tank);
                for (ServerItem serverItem : serverItems) {
                    if (serverItem.getType() == Type.TANK && !serverItem.isDead()) {
                        graphicsContext.setFill(Color.BLACK);
                        graphicsContext.fillRect(serverItem.getX() - serverItem.getRadius() / 2, serverItem.getY() - serverItem.getRadius() / 2, serverItem.getRadius(), serverItem.getRadius());
                        graphicsContext.setStroke(Color.RED);
                        graphicsContext.strokeLine(serverItem.getX(), serverItem.getY(), serverItem.getX() + Math.cos(serverItem.getAngle()) * 20, serverItem.getY() + Math.sin(serverItem.getAngle()) * 20);
                    } else if (serverItem.getType() == Type.SHOT) {
                        if (serverItem.getShotType() == ShotType.BULLET) {
                            graphicsContext.setFill(Color.RED);
                            graphicsContext.fillOval(serverItem.getX() + Math.cos(serverItem.getAngle()) * 20, serverItem.getY() + Math.sin(serverItem.getAngle()) * 20, 5, 5);
                        } else {
                            graphicsContext.setFill(Color.FIREBRICK);
                            graphicsContext.fillRect(serverItem.getX() + Math.cos(serverItem.getAngle()) * 20 -7.5, serverItem.getY() + Math.sin(serverItem.getAngle()) * 20, 15, 15);
                        }
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Log.error("sleep error", e);
                    break;
                }
            }
        }).start();
    }

    /**
     * Responds to keystrokes and sends a message to the server
     * @param codes key pressed by the player
     * @param tank tank controlled by a player
     */
    private void control(Collection<String> codes, AddTank tank) {
        if (codes.contains("LEFT")) {
            client.sendTCP(new Move(tank.getClientId(), false));
        }
        if (codes.contains("SPACE")) {
            client.sendTCP(new MakeShot(tank.getClientId(), ShotType.BULLET));
        }
        if (codes.contains("RIGHT")) {
            client.sendTCP(new Move(tank.getClientId(), true));
        }
        if (codes.contains("UP")) {
            client.sendTCP(new CannonMove(tank.getClientId(), true));
        }
        if (codes.contains("DOWN")) {
            client.sendTCP(new CannonMove(tank.getClientId(), false));
        }
        if (codes.contains("B")) {
            client.sendTCP(new MakeShot(tank.getClientId(), ShotType.BOMB));
        }

    }

    /**
     * Draws a game map
     * @param graphicsContext graphicsContext
     * @throws IOException IOException
     */
    private void draw(GraphicsContext graphicsContext) throws IOException {
        land = new ArrayList<>();
        for (Point point : gameMap.points) {
            int x = (int) point.getX();
            int y = (int) point.getY();
            land.add(new Point2D(x, y));
        }
        graphicsContext.setStroke(Color.BROWN);
        land.stream().reduce((p1, p2) -> {
            graphicsContext.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            return p2;
        });
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
