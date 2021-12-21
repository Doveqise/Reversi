package SaveAndLoad.SLButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import GameFrame.ReversiFrame;
import MainMenu.GameMenu;
import Reversi.Reversi;
import SaveAndLoad.SaveLoadFrame;
import SkinsAndSounds.Skins;

public class BackButton extends JButton {
    public BackButton(){
        this.setFont(new Font("Calibri", Font.BOLD, 60) );
        this.setText("Back");

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                onMouseClicked();
            }
        });
    }

    public void onMouseClicked(){
        GameMenu.saveLoadFrame.setVisible(false);
        if(SaveLoadFrame.saveOrLoad== 0){
            Reversi.mainMenu.setVisible(true);
        }else{
            ReversiFrame.controller.reSetTime();
            GameMenu.reversiFrame.setVisible(true);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        Image buttonImage= Skins.buttonImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(buttonImage) );
        setHorizontalTextPosition(SwingConstants.CENTER);
        super.paintComponent(g);
    }
}
