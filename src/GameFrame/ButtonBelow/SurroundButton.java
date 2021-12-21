package GameFrame.ButtonBelow;

import GameFrame.ChessBoard.ChessGrid;
import GameFrame.ReversiFrame;
import MainMenu.GameMenu;

import javax.swing.*;

public class SurroundButton extends BasicButton{
    public SurroundButton(int w, int h){
        super(w, h);
        setText("Surround");
    }

    @Override
    public void onMouseClicked(){
        if(!ChessGrid.reactflag){
            return;
        }
        int result = JOptionPane.showConfirmDialog(
                GameMenu.reversiFrame,
                "If surround?",
                "Information",
                JOptionPane.YES_NO_OPTION
        );
        if(result== 0){
            ReversiFrame.controller.surround(ReversiFrame.controller.currentChess);
        }
    }
}