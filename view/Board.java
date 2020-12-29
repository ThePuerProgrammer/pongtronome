package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Ball;
import model.Component;
import model.Paddle;

public class Board implements Runnable {

    private JFrame window;
    private ArrayList<Component> components;
    private int FPS = 60;
    private final int TIME = 1000 / FPS;
    private Thread thread;
    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 300;
    public static final int BALL_WIDTH = 10;
    private static int paddleY = BOARD_HEIGHT / 2;
    private static int ballX = BOARD_WIDTH / 2;
    private Canvas canvas;
    private Ball ball;
    private Paddle lPaddle;
    private Paddle rPaddle;

    private enum State {
        STANDBY, RUNNING
    }

    private State state;

    public Board(JFrame window) {
        this.window = window;
        components = new ArrayList<>();
        state = State.STANDBY;
    }

    public void init() {
        Container cp = window.getContentPane();
        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        canvas = new Canvas(this);

        cp.add(BorderLayout.CENTER, centerPanel);
        centerPanel.add(BorderLayout.CENTER, canvas);
        ball = new Ball((BOARD_WIDTH / 2) - 5, BOARD_HEIGHT / 2, 10, 10, Color.WHITE);
        lPaddle = new Paddle(0, 10, 40, Color.WHITE);
        rPaddle = new Paddle(BOARD_WIDTH - 10, 10, 40, Color.WHITE);
        components.add(ball);
        components.add(lPaddle);
        components.add(rPaddle);

        canvas.repaint();
        start();
    }

    private void start() {
        state = State.RUNNING;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (state == State.RUNNING) {
            // To aim for consistent FPS
            long nano = System.nanoTime();
            tick();
            canvas.repaint();
            long updated = System.nanoTime() - nano;
            long buffer = TIME - updated / 1_000_000;
            if (buffer <= 0) {
                buffer = 5;
            }
            try {
                Thread.sleep(buffer);
            } catch (Exception e) {
                System.out.println("Caught Exception - Thread.sleep(buffer)");
                System.exit(1);
            }
        }
    }

    private void tick() {
        paddleY = ball.getY();
        ballX = ball.getX();
        for (var c: components) {
            c.animate();
        }
    }

    public static int getPaddleY() {
        return paddleY;
    }

    public static int getBallX() {
        return ballX;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }
}
