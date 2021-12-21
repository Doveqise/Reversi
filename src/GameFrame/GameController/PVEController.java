package GameFrame.GameController;

import GameFrame.AIFoolish.*;
import GameFrame.ReversiFrame;
import GameFrame.StepRecorder.StepList;
import MainMenu.GameMenu;

import javax.swing.*;

public class PVEController extends BasicController{
    public PVEController(String blackPlayerName, String whitePlayerName, int x, AI ai) {
        name= "PVE";
        this.blackPlayerName = blackPlayerName;
        this.whitePlayerName = whitePlayerName;
        currentChess= x;
        this.ai= ai;
    }

    public String sendMessage(){
        return null;
    }

    public void receiveMessage(String message){

    }
    @Override
    public void unDo(){
        StepList stepList= stepState.stepList;
        int cnt= stepList.cnt;
        if(cnt== 1&& stepList.steps[cnt].name.equals("BOT")&& stepList.steps[cnt].cheat== false){
            JOptionPane.showMessageDialog(
                    GameMenu.reversiFrame,
                    "Already no step to undo!",
                    "Infromation",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        while(stepList.steps[cnt].name.equals("BOT")&& stepList.steps[cnt].cheat== false){
            cnt--;
            unDoOnce();
        }
        unDoOnce();
    }

    @Override
    public void click(int x, int y){
        if(ReversiFrame.controller.cheat){
            int nowChess= ReversiFrame.controller.currentChess;
            placeChessAndThen(x, y);
            if(nowChess!= ReversiFrame.controller.currentChess){
                ReversiFrame.controller.swapPlayer();
            }
            return;
        }
        placeChessAndThen(x, y);
        if(ReversiFrame.controller.blackNum+ ReversiFrame.controller.whiteNum== 64){
            return;
        }
        boolean aiTurn= false;
        if(currentChess== 1&& blackPlayerName.equals("BOT") ){
            aiTurn= true;
        }
        if(currentChess== -1&& whitePlayerName.equals("BOT") ){
            aiTurn= true;
        }
        while(aiTurn){
            Position position= ai.judge();
            if(position== null){
                ReversiFrame.controller.surround(ReversiFrame.controller.currentChess);
                return;
            }
            placeChessAndThen(position.x, position.y);
            if(ReversiFrame.controller.blackNum+ ReversiFrame.controller.whiteNum== 64){
                return;
            }
            aiTurn= false;
            if(currentChess== 1&& blackPlayerName.equals("BOT") ){
                aiTurn= true;
            }
            if(currentChess== -1&& whitePlayerName.equals("BOT") ){
                aiTurn= true;
            }
        }
    }
}
