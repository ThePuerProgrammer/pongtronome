package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Canvas extends JPanel {
    private static final long serialVersionUID = 1L;
    private Board board;

    public Canvas(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(Board.BOARD_WIDTH, Board.BOARD_HEIGHT));
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect((Board.BOARD_WIDTH / 2) - 2, 0, 4, Board.BOARD_HEIGHT);

        for (var c: board.getComponents()) {
            c.render(g2);
        }
    }

}
