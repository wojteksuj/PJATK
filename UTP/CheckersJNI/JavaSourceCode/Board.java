import Tiles.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board {

    static {
        System.loadLibrary("Project1_C");
    }

    JButton[][] buttonBoard = new JButton[8][8];

    public native int[][] getBoard();

    public native void updateBoard(int i, int j);

    public native void setBoard(int[][] b);

    public native void setTour(int a);

    public void drawBoard(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (this.getBoard()[i][j]) {
                    case 0:
                        this.buttonBoard[i][j] = new BoardTile();
                        frame.add(this.buttonBoard[i][j]);
                        break;
                    case 1:
                        this.buttonBoard[i][j] = new BlueTile();
                        frame.add(this.buttonBoard[i][j]);
                        break;
                    case 2:
                        this.buttonBoard[i][j] = new RedTile();
                        frame.add(this.buttonBoard[i][j]);
                        break;
                    case 3:
                        this.buttonBoard[i][j] = new BlueTileChecked();
                        frame.add(this.buttonBoard[i][j]);
                        break;
                    case 4:
                        this.buttonBoard[i][j] = new RedTileChecked();
                        frame.add(this.buttonBoard[i][j]);
                        break;
                    case -1:
                        this.buttonBoard[i][j] = new BlueDame();
                        frame.add(this.buttonBoard[i][j]);
                        break;
                    case -2:
                        this.buttonBoard[i][j] = new RedDame();
                        frame.add(this.buttonBoard[i][j]);
                        break;
                    case -3:
                        this.buttonBoard[i][j] = new BlueDameChecked();
                        frame.add(this.buttonBoard[i][j]);
                        break;
                    case -4:
                        this.buttonBoard[i][j] = new RedDameChecked();
                        frame.add(this.buttonBoard[i][j]);
                        break;
                    default:
                        System.out.println("Error while loading a resource");
                        break;
                }


                final int row = i;
                final int col = j;

                this.buttonBoard[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateBoard(row, col);
                        drawBoard(frame);
                    }
                });
                frame.setVisible(true);
            }

        }
    }
}
