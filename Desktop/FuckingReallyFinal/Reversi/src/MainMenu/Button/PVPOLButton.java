package MainMenu.Button;

import GameFrame.CheatModePanel.CheatPanel;
import Clients.ClientBlack;
import Clients.ClientWhite;
import GameFrame.GameController.PVPOLController;
import GameFrame.ReversiFrame;
import GameFrame.StepRecorder.StepList;
import MainMenu.GameMenu;
import Reversi.Reversi;

import javax.swing.*;

public class PVPOLButton extends BasicButton{
    public static Thread thread;
    public PVPOLButton(){
        super();
        setText("PVP Online");
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

        try{
            if(result== 0){

                StepList s = new StepList();
                PVPOLController c = new PVPOLController(name1, "unknown", 1);
                Reversi.mainMenu.setVisible(false);
                GameMenu.reversiFrame= new ReversiFrame(1200, 900, s, c);
                Thread thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            ClientBlack.run();
                        } catch (Exception e) {
                            System.out.println("Time error");
                        }
                    }
                });
                thread.start();
            } else{
                StepList s = new StepList();
                PVPOLController c = new PVPOLController("unknown", name1, 1);
                Reversi.mainMenu.setVisible(false);
                GameMenu.reversiFrame= new ReversiFrame(1200, 900, s, c);
                thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            ClientWhite.run();
                        } catch (Exception e) {
                            System.out.println("Time error");
                        }
                    }
                });
                thread.start();
            }
        }catch(Exception e){

        }
        CheatPanel.cheatButton.setVisible(false);
        GameMenu.reversiFrame.saveButton.setVisible(false);
        GameMenu.reversiFrame.reStartButton.setVisible(false);
    }
}
