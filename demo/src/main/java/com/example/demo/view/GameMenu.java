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
        logicFunctions(now ,progressIndicator);
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
        drawBalls(gc);
    }

    private void drawCenterCircle(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillOval(WIDTH / 2 - controller.getBigBallRadius(),
                HEIGHT / 2 - controller.getBigBallRadius(),
                2 * controller.getBigBallRadius(),
                2 * controller.getBigBallRadius());
    }

    private void drawBalls(GraphicsContext context) {
        for (Ball ball : controller.getBalls())
            if ((ball.isShot() || ball.isReadyToLaunch()) && ball.isVisible()) {
                if (ball.isConnect()) {
                    double centerX = ball.getBallX();
                    double centerY = ball.getBallY();
                    double circleCenterX = WIDTH / 2;
                    double circleCenterY = HEIGHT / 2;
                    double lineWidth = 5.0; // Change this value to adjust line width
                    context.setLineWidth(lineWidth);
                    context.strokeLine(centerX, centerY, circleCenterX, circleCenterY);
                }
                context.setFill(Color.BLACK);
                context.fillOval(ball.getBallX() - ball.getBallRadius(),
                        ball.getBallY() - ball.getBallRadius(),
                        2 * ball.getBallRadius(),
                        2 * ball.getBallRadius());
                context.setFill(Color.WHITE);
                if (ball.getNumber() > 9)
                    context.fillText(String.valueOf(ball.getNumber()), ball.getBallX() - 11, ball.getBallY() + 7);
                else
                    context.fillText(String.valueOf(ball.getNumber()), ball.getBallX() - 5, ball.getBallY() + 7);
            }
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
