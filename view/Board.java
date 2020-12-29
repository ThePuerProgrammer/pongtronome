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

public class Board implements Runnable {

    private JFrame window;
    private ArrayList<Component> components;
    private int FPS = 60;
    private final int TIME = 1000 / FPS;
    private Thread thread;
    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 300;
    public static final int BALL_WIDTH = 10;
    private Canvas canvas;
    private Ball ball;

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
        ball = new Ball(BOARD_HEIGHT / 2, BOARD_WIDTH / 2, 10, 10, Color.WHITE);
        components.add(ball);

        canvas.repaint();
    }

    private void start() {
        state = State.RUNNING;
        thread = new Thread();
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
        for (var c: components) {
            c.animate();
        }
    }

    public ArrayList<Component> getComponents() {
        return components;
    }
}
