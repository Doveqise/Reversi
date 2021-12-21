package GameFrame.ChessBoard;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel{
    public ChessGrid[][] chessGrids;

    public ChessBoard(int size, int[][] board){
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setSize(size, size);
        chessGrids= new ChessGrid[10][10];
        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                ChessGrid g= new ChessGrid(size/8, board[i][j], i, j);
                g.setLocation((j- 1)* size/8, (i- 1)* size/8);
                chessGrids[i][j]= g;
                this.add(chessGrids[i][j]);
                g.setSize(size/8, size/8);
            }
        }
    }

    public void renewChessBoard(int[][] board){
        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                chessGrids[i][j].chess= board[i][j];
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
