package com.example.demo.view;


import com.example.demo.model.Ball;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


import static com.example.demo.view.SomeFields.HEIGHT;
import static com.example.demo.view.SomeFields.WIDTH;


public class GameMenu extends Application {

    final private GameMenuController controller = GameMenuController.getInstance();

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.setCenter(canvas);

        Scene scene = new Scene(root);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(0.0);
        progressIndicator.setPrefSize(50, 50);

        FlowPane progressPane = new FlowPane();
        progressPane.getChildren().add(progressIndicator);
        progressPane.setPrefHeight(0);
        progressIndicator.setProgress(0);
        root.setTop(progressPane);

        stage.setScene(scene);
        stage.setTitle("Game Menu");
        stage.show();


        new AnimationTimer() {

            @Override
            public void handle(long now) {

                functionList(gc, now, progressIndicator);

                if (controller.isGameOver()) {
                    checkGameOver(root, gc);
                    stop();
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                        Stage stage = (Stage) root.getScene().getWindow();
                        stage.close();
                    }));
                    timeline.play();
                }

                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ESCAPE) {
                        controller.stopTimer();
                        stop();
                    } else if (event.getCode() == KeyCode.A) {
                        start();
                    } else if (event.getCode() == KeyCode.SPACE) {
                        controller.shootBall();
                    } else if (event.getCode() == KeyCode.LEFT) {
                        controller.moveToLeft();
                    } else if (event.getCode() == KeyCode.RIGHT) {
                        controller.moveToRight();
                    } else if (event.getCode() == KeyCode.TAB) {
                        controller.startIceProgress();
                    }
                });

            }
        }.start();

    }

    private void functionList(GraphicsContext gc, long now, ProgressIndicator progressIndicator) {
        graphicsContextFunctions(gc);
        logicFunctions(now, progressIndicator);
    }

    private void logicFunctions(long now, ProgressIndicator progressIndicator) {
        controller.setTimer(now);
        controller.getReadyToLaunch();
        controller.moveBalls();
        controller.doFazesFunctions();
        progressIndicator.setProgress(controller.getIceProgressPercent());
        controller.stopIceProgress();
    }

    private void graphicsContextFunctions(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        writeDetails(gc);
        drawCenterCircle(gc);
        drawGameBalls(gc);
        drawDefaultBalls(gc);
    }

    private void drawCenterCircle(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillOval(controller.getCircleCenterX() - controller.getBigBallRadius(),
                controller.getCircleCenterY() - controller.getBigBallRadius(),
                2 * controller.getBigBallRadius(),
                2 * controller.getBigBallRadius());
    }

    private void drawGameBalls(GraphicsContext context) {
        for (Ball ball : controller.getBalls())
            if ((ball.isShot() || ball.isReadyToLaunch()) && ball.isVisible()) {
                if (ball.isConnect()) {
                    drawLine(context, ball);
                }
                drawBall(context, ball);
                writeNumberOnBall(context, ball);
            }
    }

    private void drawDefaultBalls(GraphicsContext gc) {
        for (Ball ball : controller.getDefaultBalls()) {
            if (ball.isVisible()) {
                drawLine(gc, ball);
                drawBall(gc, ball);
            }
        }
    }

    public void drawBall(GraphicsContext gc, Ball ball) {
        gc.setFill(Color.BLACK);
        gc.fillOval(ball.getBallX() - ball.getBallRadius(),
                ball.getBallY() - ball.getBallRadius(),
                2 * ball.getBallRadius(),
                2 * ball.getBallRadius());
    }

    public void writeNumberOnBall(GraphicsContext context, Ball ball) {
        context.setFill(Color.WHITE);
        if (ball.getNumber() > 9)
            context.fillText(String.valueOf(ball.getNumber()), ball.getBallX() - 11, ball.getBallY() + 7);
        else
            context.fillText(String.valueOf(ball.getNumber()), ball.getBallX() - 5, ball.getBallY() + 7);
    }

    public void drawLine(GraphicsContext gc, Ball ball) {
        gc.setLineWidth(controller.getLineWidth());
        gc.strokeLine(ball.getBallX(), ball.getBallY(), controller.getCircleCenterX(), controller.getCircleCenterY());
    }


    private void writeDetails(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.setFont(Font.font(20));
        context.fillText("Wind Speed: " + String.format("%.1f", controller.getWindSpeed()), 10, 30);
        context.fillText("Time= " + String.format("%02d", controller.getMinutes()) + ":" +
                String.format("%02d", controller.getSeconds()), 10, 60);
        context.fillText("Magic Force Degree : " + String.format("%02.2f", controller.getMagicForceDegree()) + "°", 10, 90);
    }


    private void checkGameOver(BorderPane root, GraphicsContext gc) {
        graphicsContextFunctions(gc);
        if (controller.isWin())
            root.setBackground(Background.fill(Color.GREEN));
        else
            root.setBackground(Background.fill(Color.RED));
        controller.stopTimer();
    }
}
