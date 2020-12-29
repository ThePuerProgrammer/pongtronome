package model;

import java.awt.Graphics2D;

import controller.SoundListener;
import view.Board;

import java.awt.Color;

public class Ball extends Component implements SoundSubject {

    private SoundListener listener;

    private int bpm = 120;
    private boolean up = true;
    private boolean left = true;
    private int playArea = Board.BOARD_WIDTH - 20 - (Board.BALL_WIDTH / 2);
    private int speed = bpm * playArea / 60 / 60;
    private float fSpeed = bpm * playArea / 60 / 60;
    private float fPoint = fSpeed - (float) speed;
    private int carry = fPoint > 0 ? (int) (fPoint * 100) : 2_000_000_000;
    private int counter = carry;

    public Ball(int x, int y, int w, int h, Color c) {
        super(x, y, w, h, c);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(c);
        g2.fillRect(x, y, w, h);
    }

    @Override
    public void animate() {
        counter += carry;
        if (up) {
            super.y -= speed;
            if (super.y - w / 2 <= 0)
                up = false;
        } else {
            super.y += speed;
            if (super.y + w / 2 >= Board.BOARD_HEIGHT)
                up = true;
        }

        if (left) {
            if (counter >= 100) {
                counter = counter - 100;
                super.x -= speed + 1;
            } else {
                super.x -= speed;
            }
            if (super.x - w / 2 <= 10) {
                left = false;
                // play sound
                notifyListener();
            }
        } else {
            if (counter >= 100) {
                counter = counter - 100;
                super.x += speed + 1;
            } else {
                super.x += speed;
            }
            if (super.x >= Board.BOARD_WIDTH - w * 2) {
                left = true;
                // play sound
                notifyListener();
            }
        }
    }

    public int getY() {
        return super.y;
    }

    public int getX() {
        return super.x;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
        playArea = Board.BOARD_WIDTH - 20 - (Board.BALL_WIDTH / 2);
        speed = bpm * playArea / 60 / 60;
        fSpeed = bpm * playArea / 60 / 60;
        fPoint = fSpeed - (float) speed;
        carry = fPoint > 0 ? (int) (fPoint * 100) : 2_000_000_000;
        counter = carry;
    }

    @Override
    public void addListener(SoundListener soundListener) {
        listener = soundListener;
    }

    @Override
    public void removeListener(SoundListener soundListener) {
        listener = null;
    }

    @Override
    public void notifyListener() {
        listener.playSound();
    }
}
