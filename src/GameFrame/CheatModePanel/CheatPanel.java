package GameFrame.CheatModePanel;

import GameFrame.ReversiFrame;
import MainMenu.GameMenu;

import javax.swing.*;
import java.awt.*;

public class CheatPanel extends JPanel {
    static public CheatButton cheatButton;
    static public SwapButton swapButton;
    static public ChessIndicate chessIndicate;

    public CheatPanel(){
        super();
        this.setLayout(null);
        cheatButton= new CheatButton();

        cheatButton.setVisible(true);
        cheatButton.setSize(200, 50);
        cheatButton.setLocation(50, 0);
        this.add(cheatButton);

        swapButton= new SwapButton();
        swapButton.setVisible(ReversiFrame.controller.cheat);
        swapButton.setSize(200, 50);
        swapButton.setLocation(50, 50);
        this.add(swapButton);

        chessIndicate= new ChessIndicate(100, 80);
        chessIndicate.setVisible(true);
        chessIndicate.setLocation(300, 0);
        this.add(chessIndicate);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
