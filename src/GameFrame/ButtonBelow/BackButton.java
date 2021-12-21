package GameFrame.ButtonBelow;

import GameFrame.ChessBoard.ChessGrid;
import MainMenu.Button.PVPOLButton;
import MainMenu.GameMenu;
import Reversi.Reversi;

public class BackButton extends BasicButton{
    public BackButton(int w, int h){
        super(w, h);
        setText("Back");
    }

    @Override
    public void onMouseClicked(){
        if(!ChessGrid.reactflag){
            return;
        }
        GameMenu.reversiFrame.setVisible(false);
        Reversi.mainMenu.setVisible(true);
        if(PVPOLButton.thread!= null){
            PVPOLButton.thread.interrupt();
        }
    }
}
