package model;
import view.Board;
import java.awt.Graphics2D;
import java.awt.Color;

public class Paddle extends Component {

    public Paddle(int x, int w, int h, Color c) {
        super(x, Board.getPaddleY(), w, h, c);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(c);
        g2.fillRect(x, y, w, h);
    }

    @Override
    public void animate() {
        if (Math.abs(Board.getBallX() - super.x) >= Board.BOARD_WIDTH / 2) {
            return;
        } else if (Math.abs(Board.getBallX() - super.x) >= Board.BOARD_WIDTH - 50) {
            if (Board.getPaddleY() < Board.BOARD_HEIGHT / 2) {
                super.y -= 1;
            } else {
                super.y += 1;
            }
        } else if (Math.abs(Board.getBallX() - super.x) >= Board.BOARD_WIDTH - 100) {
            if (Board.getPaddleY() < Board.BOARD_HEIGHT / 2) {
                super.y -= 2;
            } else {
                super.y += 2;
            }
        } else if (Math.abs(Board.getBallX() - super.x) >= Board.BOARD_WIDTH - 150) {
            if (Board.getPaddleY() < Board.BOARD_HEIGHT / 2) {
                super.y -= 3;
            } else {
                super.y += 3;
            }
        } else {
            y = Board.getPaddleY() - h / 2;
        }
    }
    
}
