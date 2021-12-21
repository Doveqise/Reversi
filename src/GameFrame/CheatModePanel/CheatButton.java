package GameFrame.CheatModePanel;

import GameFrame.ReversiFrame;
import MainMenu.GameMenu;
import SkinsAndSounds.Skins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheatButton extends JButton {
    private boolean canFlush= true;

    public CheatButton(){
        this.setFont(new Font("Calibri", Font.BOLD, 25) );
        repaint();

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

    public void onMouseClicked(){
        ReversiFrame.controller.cheat= !ReversiFrame.controller.cheat;
        CheatPanel.swapButton.setVisible(ReversiFrame.controller.cheat);
        repaint();
    }

    public void onMouseEntered(){
        this.setFont(new Font("Calibri", Font.BOLD, 18) );
        canFlush= false;
        this.setText("<html>If used to place chess<br>can't save to RankList<html>");
    }

    public void onMouseExited(){
        this.setFont(new Font("Calibri", Font.BOLD, 25) );
        canFlush= true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image buttonImage = Skins.buttonImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(buttonImage));
        setHorizontalTextPosition(SwingConstants.CENTER);
        if (!canFlush) {
            return;
        }
        if (ReversiFrame.controller.cheat == true) {
            setText("CheatMode ON");
        }
        if (ReversiFrame.controller.cheat == false) {
            setText("CheatMode OFF");
        }
    }
}
