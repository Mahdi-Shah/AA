package com.example.demo.view;


import com.example.demo.model.Ball;
import com.example.demo.model.DataBase;
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
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);
        canvas.setHeight(HEIGHT);
        canvas.setWidth(WIDTH);

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
                        try {
                            (new MainMenu()).start(stage);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }));
                    timeline.play();
                }

                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ESCAPE) {
                        controller.stopTimer();
                        stop();
                        try {
                            (new PauseMenu()).start(stage);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else if (event.getCode() == GameMenuController.getInstance().getShootFirstKey()) {
                        controller.shootBall(true);
                    } else if (event.getCode() == GameMenuController.getInstance().getLeftFirstKey()) {
                        controller.moveToLeft(true);
                    } else if (event.getCode() == GameMenuController.getInstance().getRightFirstKey()) {
                        controller.moveToRight(true);
                    } else if (event.getCode() == GameMenuController.getInstance().getIceKey()) {
                        controller.startIceProgress();
                    } else if (event.getCode() == GameMenuController.getInstance().getShootSecondKey()) {
                        controller.shootBall(false);
                    } else if (event.getCode() == GameMenuController.getInstance().getLeftSecondKey()) {
                        controller.moveToLeft(false);
                    } else if (event.getCode() == GameMenuController.getInstance().getRightSecondKey()) {
                        controller.moveToRight(false);
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
        controller.getReadyToLaunch(true);
        controller.getReadyToLaunch(false);
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
                if (!ball.isDefaultBall())
                    writeNumberOnBall(context, ball);
            }
    }

    public void drawBall(GraphicsContext gc, Ball ball) {
        gc.setFill(GameMenuController.getInstance().getBallColor(ball));
        gc.fillOval(ball.getBallX() - ball.getBallRadius(),
                ball.getBallY() - ball.getBallRadius(),
                2 * ball.getBallRadius(),
                2 * ball.getBallRadius());
    }

    public void writeNumberOnBall(GraphicsContext context, Ball ball) {
        context.setFont(Font.font(16));
        context.setFill(Color.WHITE);
        if (ball.getNumber() > 9)
            context.fillText(String.valueOf(ball.getNumber()), ball.getBallX() - 9, ball.getBallY() + 6);
        else
            context.fillText(String.valueOf(ball.getNumber()), ball.getBallX() - 4, ball.getBallY() + 5);
    }

    public void drawLine(GraphicsContext gc, Ball ball) {
        gc.setLineWidth(controller.getLineWidth());
        gc.strokeLine(ball.getBallX(), ball.getBallY(), controller.getCircleCenterX(), controller.getCircleCenterY());
    }


    private void writeDetails(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.setFont(Font.font(20));
        context.fillText("Wind Speed: " + String.format("%.1f", controller.getWindSpeed()), 10, 90);
        context.fillText("Time= " + String.format("%02d", controller.getMinutes()) + ":" +
                String.format("%02d", controller.getSeconds()), 10, 120);
        context.fillText("Magic Force Degree : " + String.format("%02.2f", controller.getMagicForceDegree()) + "°", 10, 150);
        context.fillText("First player\nballs remain = ", 10, 180);
        context.setFill(controller.getFirstPlayerBallsRemainColor());
        context.fillText(controller.getFirstPlayerBallsRemain(), 140, 206);
        if (controller.isTwoSomeGame()) {
            context.setFill(Color.BLACK);
            context.fillText("Second player\nballs remain = ", 10, 240);
            context.setFill(controller.getSecondPlayerBallsRemainColor());
            context.fillText(controller.getSecondPlayerBallsRemain(), 140, 270);
        }
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
