package MainMenu.Button;

import GameFrame.AIFoolish.Greedy;
import GameFrame.AIFoolish.Monkey;
import GameFrame.GameController.PVEController;
import GameFrame.ReversiFrame;
import GameFrame.StepRecorder.Step;
import GameFrame.StepRecorder.StepList;
import MainMenu.GameMenu;
import Reversi.Reversi;

import javax.swing.*;
import java.awt.*;

public class EasyButton extends BasicButton{
    public EasyButton(){
        super();
        setText("PVE Easy");
    }
    public void onMouseClicked(){
        String name1 = JOptionPane.showInputDialog(this, "input the player name");

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

        int result = JOptionPane.showConfirmDialog(
                GameMenu.reversiFrame,
                "If use black to start?",
                "Information",
                JOptionPane.YES_NO_OPTION
        );

        if(result== 0){
            StepList s = new StepList();
            PVEController c = new PVEController(name1, "BOT", 1, new Greedy() );
            Reversi.mainMenu.setVisible(false);
            GameMenu.reversiFrame= new ReversiFrame(1200, 900, s, c);
        } else{
            StepList s = new StepList();
            s.addStep(new Step(3, 5, 1, "BOT", false) );
            PVEController c = new PVEController("BOT", name1, -1, new Greedy() );
            Reversi.mainMenu.setVisible(false);
            GameMenu.reversiFrame= new ReversiFrame(1200, 900, s, c);
        }
    }
}
