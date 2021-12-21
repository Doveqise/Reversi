package RankList.Buttons;

import RankList.PlayerList;

public class RankMonkeyButton extends BasicButton{
    public RankMonkeyButton(int w, int h){
        super(w, h);
        this.setText("Rank in Monkey");
    }

    @Override
    public void onMouseClicked(){
        PlayerList.rank(3);
    }
}
