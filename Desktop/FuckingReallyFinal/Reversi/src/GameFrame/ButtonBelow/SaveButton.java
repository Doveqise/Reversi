package GameFrame.ButtonBelow;

import GameFrame.ReversiFrame;
import MainMenu.GameMenu;
import SaveAndLoad.SaveLoadFrame;
import SaveAndLoad.ScreenShot.ScreenShot;

import java.awt.*;

public class SaveButton extends BasicButton{
    public SaveButton(int w, int h){
        super(w, h);
        setText("Save");
    }

    @Override
    public void onMouseClicked(){
        ReversiFrame reversiFrame= GameMenu.reversiFrame;
        Point point= reversiFrame.getLocation();
        int width= reversiFrame.getWidth();
        int height= reversiFrame.getHeight();
        SaveLoadFrame.image= ScreenShot.captureScreen(point.x+ 10, point.y+ 40, width- 20, height+ 20);
        GameMenu.reversiFrame.setVisible(false);
        GameMenu.saveLoadFrame.setVisible(true);
        SaveLoadFrame.saveOrLoad= 1;
        SaveLoadFrame.currentFrame= GameMenu.reversiFrame;
    }
}