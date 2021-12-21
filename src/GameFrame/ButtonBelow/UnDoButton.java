package GameFrame.ButtonBelow;

import GameFrame.ChessBoard.ChessGrid;
import GameFrame.ReversiFrame;
import MainMenu.GameMenu;

public class UnDoButton extends BasicButton{
    public UnDoButton(int w, int h){
        super(w, h);
        setText("UnDo");
    }

    @Override
    public void onMouseClicked(){
        if(!ChessGrid.reactflag){
            return;
        }
        ReversiFrame.controller.unDo();
    }
}