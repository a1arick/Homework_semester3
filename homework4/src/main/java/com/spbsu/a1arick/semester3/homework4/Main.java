package com.spbsu.a1arick.semester3.homework4;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.LinkedHashSet;


/** Main class for game */
public class Main extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 300;

    /** Start The Game. */
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle( "tanks" );
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
        World world = new World(WIDTH, HEIGHT);
        Player tank = new Player(world);
        world.addTank(tank);

        world.addTank(new Bot(100.0, Color.BROWN, world));
        world.addTank(new Bot(300.0, Color.CORAL, world));
        world.addTank(new Bot(500.0, Color.GREEN, world));
        world.addTank(new Bot(700.0, Color.BLUE, world));
        world.addTank(new Bot(900.0, Color.YELLOW, world));

        final long[] time = {System.nanoTime()};

        new AnimationTimer()
        {
            public void handle(long currentTime)
            {
                double elapsedTime = (currentTime - time[0]) / 1000000000.0;
                time[0] = currentTime;

                tank.control(codes);
                world.update(elapsedTime);
                world.draw(graphicsContext);
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
