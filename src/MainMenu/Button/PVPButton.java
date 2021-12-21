package MainMenu.Button;

import GameFrame.GameController.PVPController;
import GameFrame.ReversiFrame;
import GameFrame.StepRecorder.StepList;
import MainMenu.GameMenu;
import Reversi.Reversi;

import javax.swing.*;
import java.awt.*;

public class PVPButton extends BasicButton{
    public PVPButton(){
        super();
        setText("New PVP");
    }

    public void onMouseClicked(){
        String name1 = JOptionPane.showInputDialog(this, "input the black player name");
        if(name1== null){
            return;
        }
        if(name1.equals("") ){
            JOptionPane.showMessageDialog(
                    Reversi.mainMenu,
                    String.format("no name is invalid"),
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        String name2 = JOptionPane.showInputDialog(this, "input the white player name");
        if(name2== null){
            return;
        }
        if(name2.equals("") ){
            JOptionPane.showMessageDialog(
                    Reversi.mainMenu,
                    String.format("no name is invalid"),
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        Reversi.mainMenu.setVisible(false);
        StepList s = new StepList();
        PVPController c = new PVPController(name1, name2, 1);
        GameMenu.reversiFrame= new ReversiFrame(1200, 900, s, c);
    }
}
