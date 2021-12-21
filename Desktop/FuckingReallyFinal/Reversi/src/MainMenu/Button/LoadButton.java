package MainMenu.Button;

import MainMenu.GameMenu;
import Reversi.Reversi;
import SaveAndLoad.SaveLoadFrame;

import java.awt.*;

public class LoadButton extends BasicButton{
    public LoadButton(){
        super();
        setText("Load Game");
    }
    public void onMouseClicked(){
        Reversi.mainMenu.setVisible(false);
        GameMenu.saveLoadFrame.setVisible(true);
        SaveLoadFrame.saveOrLoad= 0;
    }
}
