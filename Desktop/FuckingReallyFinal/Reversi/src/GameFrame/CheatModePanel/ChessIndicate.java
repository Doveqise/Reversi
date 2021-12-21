package GameFrame.CheatModePanel;

import GameFrame.ReversiFrame;
import MainMenu.GameMenu;
import SkinsAndSounds.Skins;

import javax.swing.*;
import java.awt.*;

public class ChessIndicate extends JComponent {
    public int gridSize, chessSize;

    public ChessIndicate(int gridSize, int chessSize){
        super();
        this.setSize(gridSize, gridSize);
        this.chessSize= chessSize;
        this.gridSize= gridSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image gridImage= Skins.gridImage.getScaledInstance(gridSize- 2, gridSize- 2, Image.SCALE_FAST);
        g.drawImage(gridImage, 1, 1, null);
        Image chessImage= (ReversiFrame.controller.currentChess== 1? Skins.blackChessImage: Skins.whiteChessImage);
        chessImage= chessImage.getScaledInstance(chessSize, chessSize, Image.SCALE_FAST);
        g.drawImage(chessImage, (gridSize- chessSize)/ 2, (gridSize- chessSize)/ 2, null);
    }
}
