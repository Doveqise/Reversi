package MainMenu.Button;

import Reversi.Reversi;

public class RankButton extends BasicButton{
    public RankButton(){
        super();
        setText("Rank List");
    }
    public void onMouseClicked(){
        Reversi.rankListFrame.setVisible(true);
        Reversi.mainMenu.setVisible(false);
    }
}
