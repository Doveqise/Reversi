package GameFrame.GameController;

import GameFrame.ReversiFrame;
import MainMenu.GameMenu;

public class PVPController extends BasicController{
    public PVPController(String blackPlayerName, String whitePlayerName, int x) {
        name= "PVP";
        this.blackPlayerName = blackPlayerName;
        this.whitePlayerName = whitePlayerName;
        currentChess= x;
    }

    public String sendMessage(){
        return null;
    }

    public void receiveMessage(String message){

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
    }

    @Override
    public void unDo(){
        unDoOnce();
    }
}
