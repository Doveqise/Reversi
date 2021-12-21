package GameFrame.ButtonBelow;

import GameFrame.ChessBoard.ChessGrid;
import GameFrame.ReversiFrame;
public class ReStartButton extends BasicButton{
    public ReStartButton(int w, int h){
        super(w, h);
        setText("ReStart");
    }

    @Override
    public void onMouseClicked(){
        if(!ChessGrid.reactflag){
            return;
        }
        ReversiFrame.controller.reNew();
    }
}
