package model;

import java.awt.Graphics2D;

import view.Board;

import java.awt.Color;

public class Ball extends Component {

    private int bpm = 120;
    private boolean up = true;
    private boolean left = true;
    private int playArea = Board.BOARD_WIDTH - 20 - (Board.BALL_WIDTH / 2);
    private int speed = bpm * playArea / 60 / 60;
    private float fSpeed = bpm * playArea / 60 / 60;
    private float fPoint = fSpeed - (float)speed;
    private int carry = fPoint > 0 ? (int)(100.0 / (fPoint * 100)) : 2_000_000_000;
    private int counter = 0;

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
        counter++;
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
            if (counter == carry) {
                counter = 0;
                super.x -= speed + 1;
            } else {
                super.x -= speed;
            }
            if (super.x - w / 2 <= 10) 
                left = false;
        } else {
            if (counter == carry) {
                counter = 0;
                super.x += speed + 1;
            } else {
                super.x += speed;
            }
            if (super.x >= Board.BOARD_WIDTH - w * 2)
                left = true;
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
    }
}
