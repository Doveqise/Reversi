package RankList.Buttons;

import GameFrame.ButtonBelow.BasicButton;
import MainMenu.GameMenu;
import Reversi.Reversi;

import java.awt.*;

public class BackButton extends BasicButton {
    public BackButton(int w, int h){
        super(w, h);
        this.setFont(new Font("Calibri", Font.BOLD, 50) );
        setText("Back");
    }

    @Override
    public void onMouseClicked(){
        Reversi.rankListFrame.setVisible(false);
        Reversi.mainMenu.setVisible(true);
    }
}
