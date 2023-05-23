package com.example.demo.view;


import com.example.demo.model.Ball;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.FillRule;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import static com.example.demo.view.SomeFields.HEIGHT;
import static com.example.demo.view.SomeFields.WIDTH;


public class GameMenu extends Application {
    int timeLimitForShow = 1;

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

                if (controller.isGameOver()) {
                    checkGameOver(root, gc);
                    if (controller.isWin())
                        controller.moveWinGameBalls();
                    else if (controller.isSecondPlayerWin()) {
                        controller.moveSecondPlayerGameWinBalls(now);
                    }
                    else
                        controller.moveLostGameBalls();
                    timeLimitForShow++;
                    if (timeLimitForShow > 50) {
                        stop();
                        try {
                            (new ScoreMenu()).start(stage);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else
                    functionList(gc, now, progressIndicator);

                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == GameMenuController.getInstance().getStopKey()) {
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
        graphicsContextFunctions(gc, now);
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

    private void graphicsContextFunctions(GraphicsContext gc, double now) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        if (!controller.isGameOver())
            writeDetails(gc);
        drawCenterCircle(gc, now);
        drawGameBalls(gc, now);
    }

    private void drawCenterCircle(GraphicsContext graphicsContext, double now) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setGlobalAlpha(getFade(now));
        graphicsContext.fillOval(controller.getCircleCenterX() - controller.getBigBallRadius(),
                controller.getCircleCenterY() - controller.getBigBallRadius(),
                2 * controller.getBigBallRadius(),
                2 * controller.getBigBallRadius());
        graphicsContext.setGlobalAlpha(1);

        if (controller.isIceProgressTime()) {
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.setFont(Font.font(70));
            graphicsContext.fillText(String.format("%.1f", controller.getTimesRemainToEndIceProgress()),
                    controller.getCircleCenterX() - 45, controller.getCircleCenterY() + 20);


            graphicsContext.setFill(Color.BLACK);
            graphicsContext.fillArc(500, 500, 60, 60, 90,
                    controller.getTimesRemainToEndIceProgress() / controller.getIceProgressTime() * 360,
                    ArcType.ROUND);
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillArc(505, 505, 50, 50, 90, 360, ArcType.ROUND);
        }
    }

    private void drawGameBalls(GraphicsContext context, double now) {
        for (Ball ball : controller.getBalls())
            if ((ball.isShot() || ball.isReadyToLaunch()) && ball.isVisible()) {
                if (ball.isConnect()) {
                    drawLine(context, ball, now);
                }
                drawBall(context, ball);
                if (!ball.isDefaultBall())
                    writeNumberOnBall(context, ball);

            }
    }

    public void drawBall(GraphicsContext gc, Ball ball) {
        drawIndicator(gc, ball);
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

    public void drawLine(GraphicsContext gc, Ball ball, double now) {
        gc.setGlobalAlpha(getFade(now));
        gc.setLineWidth(controller.getLineWidth());
        gc.strokeLine(ball.getBallX(), ball.getBallY(), controller.getCircleCenterX(), controller.getCircleCenterY());
        gc.setGlobalAlpha(1);
    }


    private void writeDetails(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.setFont(Font.font(20));
        context.fillText(Labels.getLabel(Labels.WIND_SPEED) + ": " + String.format("%.1f", controller.getWindSpeed()), 10, 90);
        context.fillText(Labels.getLabel(Labels.TIME) + "= " + String.format("%02d", controller.getMinutes()) + ":" +
                String.format("%02d", controller.getSeconds()), 10, 120);
        context.fillText(Labels.getLabel(Labels.MAGIC_FORCE_DEGREE) + " : " + String.format("%02.2f", controller.getMagicForceDegree()) + "°", 10, 150);
        context.fillText(Labels.getLabel(Labels.FIRST_PLAYER_BALLS_REMAIN) + " = ", 10, 180);
        context.fillText(Labels.getLabel(Labels.FIRST_PLAYER_SCORE), 400, 120);
        context.fillText(controller.getPlayerScore(true), 560, 120);
        context.setFill(controller.getFirstPlayerBallsRemainColor());
        context.fillText(controller.getFirstPlayerBallsRemain(), 140, 206);
        if (controller.isTwoSomeGame()) {
            context.setFill(Color.BLACK);
            context.fillText(Labels.getLabel(Labels.SECOND_PLAYER_BALLS_REMAIN) + " = ", 10, 240);
            context.fillText(Labels.getLabel(Labels.SECOND_PLAYER_SCORE), 380, 150);
            context.fillText(controller.getPlayerScore(false), 560, 150);
            context.setFill(controller.getSecondPlayerBallsRemainColor());
            context.fillText(controller.getSecondPlayerBallsRemain(), 140, 270);
        }
    }


    private void checkGameOver(BorderPane root, GraphicsContext gc) {
        graphicsContextFunctions(gc, 1000000000);
        if (controller.isWin())
            root.setBackground(Background.fill(Color.GREEN));
        else
            root.setBackground(Background.fill(Color.RED));
        controller.stopTimer();
    }

    private double getFade(double now) {
        if (controller.isIceProgressTime()) {
            double temp = now;
            while (temp > 1000000000)
                temp -= 1000000000;
            return temp / 1000000000;
        } else
            return 1;
    }

    private void drawIndicator(GraphicsContext gc, Ball ball) {
        for (int i = 50; i > 10; i--) {
            int j = 0;
            if (!ball.isForFirstOpponent())
                j = 1;
            if (i % 2 == 0)
                gc.setFill(Color.WHITE);
            else
                gc.setFill(Color.BLACK);
            if (ball.isReadyToLaunch() && !ball.isShot())
                gc.fillArc(ball.getBallX() - i * ball.getBallRadius() / 10, ball.getBallY() - i * ball.getBallRadius() / 10,
                        i * 2 * ball.getBallRadius() / 10, i * 2 * ball.getBallRadius() / 10, 75 + j * 180, 30, ArcType.CHORD);
        }
    }
}
