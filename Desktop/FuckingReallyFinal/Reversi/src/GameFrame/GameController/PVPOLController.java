package GameFrame.GameController;

import GameFrame.ReversiFrame;
import GameFrame.StepRecorder.StepList;
import MainMenu.Button.PVPOLButton;
import MainMenu.GameMenu;

import javax.swing.*;

import static java.lang.Thread.sleep;

public class PVPOLController extends BasicController{
    public String myMessage= "QQQ";
    public String messageFromOther;
    public int clientChess;
    public static String surroundVoice= "Surround";
    public static String unDoVoice= "UnDo";
    public static String okVoice= "OK";
    public static String noVoice= "NO";

    public PVPOLController(String blackPlayerName, String whitePlayerName, int x) {
        name= "PVPOL";
        if(blackPlayerName.equals("unknown") ){
            clientChess= -1;
        }else{
            clientChess= 1;
        }

        this.blackPlayerName = blackPlayerName;
        this.whitePlayerName = whitePlayerName;
        currentChess= x;
    }

    @Override
    public String sendMessage(){
        while(myMessage.equals("QQQ") ){
            try{
                sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String ans= myMessage;
        myMessage= "QQQ";
        return ans;
    }

    @Override
    public void receiveMessage(String message){
        if(message.equals(surroundVoice) ){
            ReversiFrame.controller.surround(ReversiFrame.controller.currentChess);
            return;
        }
        if(message.equals(unDoVoice) ){
            int result = JOptionPane.showConfirmDialog(
                    GameMenu.reversiFrame,
                    "The competitor asking for undo? agree?",
                    "Information",
                    JOptionPane.YES_NO_OPTION
            );
            if(result== 0){
                myMessage= okVoice;
                StepList stepList= ReversiFrame.controller.stepState.stepList;
                while(!stepList.steps[stepList.cnt].name.equals("unknown") ){
                    unDoOnce();
                }
                unDoOnce();
            }
            if(result== 1){
                myMessage= noVoice;
            }
            return;
        }
        if(message.equals(okVoice) ){
            JOptionPane.showMessageDialog(
                    GameMenu.reversiFrame,
                    "The competitor agreed the undo",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
            StepList stepList= ReversiFrame.controller.stepState.stepList;
            while(stepList.steps[stepList.cnt].name.equals("unknown") ){
                unDoOnce();
            }
            unDoOnce();
            return;
        }
        if(message.equals(noVoice) ){
            JOptionPane.showMessageDialog(
                    GameMenu.reversiFrame,
                    "The competitor denied the undo",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        if(message.equals("0 0") ){
            return;
        }
        String[] str = message.split(" ");
        int x= Integer.parseInt(str[0]);
        int y= Integer.parseInt(str[1]);
        placeChessAndThen(x, y);
        if(ReversiFrame.controller.blackNum+ ReversiFrame.controller.whiteNum== 64){
            GameOver();
            PVPOLButton.thread.interrupt();
            return;
        }
        boolean aiTurn= false;
        if(currentChess== 1&& blackPlayerName.equals("unknown") ){
            aiTurn= true;
        }
        if(currentChess== -1&& whitePlayerName.equals("unknown") ){
            aiTurn= true;
        }
        if(aiTurn){
            myMessage= "0 0";
        }
    }

    @Override
    public void surround(int chess){
        myMessage= surroundVoice;
        super.surround(chess);
        try {
            PVPOLButton.thread.interrupt();
        }catch (Exception e){
            System.out.println("hua qiang");
        }
    }

    @Override
    public void click(int x, int y){
        myMessage= x+ " "+ y;
        placeChessAndThen(x, y);
        if(ReversiFrame.controller.blackNum+ ReversiFrame.controller.whiteNum== 64){
            GameOver();
            PVPOLButton.thread.interrupt();
            return;
        }
    }

    @Override
    public void unDo(){
        StepList stepList= stepState.stepList;
        if( (clientChess== 1&& stepList.cnt<= 0)||( (clientChess== -1)&& stepList.cnt<= 2) ){
            JOptionPane.showMessageDialog(
                    GameMenu.reversiFrame,
                    "Already no step to undo!",
                    "Infromation",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }else{
            myMessage= unDoVoice;
        }
    }
}
