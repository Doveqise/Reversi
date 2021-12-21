package GameFrame.ChessBoard;

import GameFrame.ReversiFrame;
import MainMenu.GameMenu;
import Reversi.Reversi;
import SkinsAndSounds.Skins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessGrid extends JComponent {
    public int chess;
    public static int gridSize;
    public static int chessSize;
    public Color color= new Color(231, 133, 28);
    public int x;
    public int y;
    public static Image blackChessImage;
    public static Image whiteChessImage;
    public static Image gridImage;
    public int changed= 0;
    public static boolean reactflag= true;

    public static void reNewTheme(){
        gridImage= Skins.gridImage.getScaledInstance(gridSize- 2, gridSize- 2, Image.SCALE_FAST);
        blackChessImage= Skins.blackChessImage.getScaledInstance(chessSize, chessSize, Image.SCALE_FAST);
        whiteChessImage= Skins.whiteChessImage.getScaledInstance(chessSize, chessSize, Image.SCALE_FAST);
    }

    public ChessGrid(int size, int chess, int x, int y){
        gridSize= size;
        chessSize= (int)(size* 0.8);
        this.chess= chess;
        this.setSize(size, size);
        this.x= x;
        this.y= y;
        if(changed== 0){
            reNewTheme();
            changed++;
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                onMouseClicked();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                onMouseEntered();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                onMouseExited();
            }
        });
    }

    public void onMouseExited(){
        if(ReversiFrame.controller.preDot[x][y]== 1){
            ReversiFrame.controller.leaveThere(x, y);
        }
    }

    public void onMouseEntered(){
        if(ReversiFrame.controller.preDot[x][y]== 1){
            ReversiFrame.controller.onThere(x, y);
        }
    }

    public void onMouseClicked(){
        if(!reactflag){
            return;
        }
        System.out.printf("Click %d %d \n", x, y);
        if(ReversiFrame.controller.cheat){
            ReversiFrame.controller.getBoard();
            if(ReversiFrame.controller.board[x][y]== 0){
                ReversiFrame.controller.click(x, y);
            }
            return;
        }
        if(ReversiFrame.controller.preDot[x][y]==1){
            ReversiFrame.controller.click(x, y);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackGround(g);
        drawPiece(g);

        if(ReversiFrame.controller.preDot[x][y]== 1){
            g.setColor(Color.RED);
            g.fillOval((gridSize- chessSize/2)/ 2, (gridSize- chessSize/2)/ 2, chessSize/2, chessSize/2);
        }

        if(ReversiFrame.controller.preGrid[x][y]== 1){
            Image chessImage= (ReversiFrame.controller.currentChess== 1? blackChessImage: whiteChessImage);
            g.drawImage(chessImage, (gridSize- chessSize)/ 2, (gridSize- chessSize)/ 2, null);
        }
    }

    public void drawBackGround(Graphics g){
        g.drawImage(gridImage, 1, 1, null);
    }

    public void drawPiece(Graphics g){
        if(chess== 0){
            return;
        }
        Image chessImage= (chess== 1? blackChessImage: whiteChessImage);
        g.drawImage(chessImage, (gridSize- chessSize)/ 2, (gridSize- chessSize)/ 2, null);
    }
}
