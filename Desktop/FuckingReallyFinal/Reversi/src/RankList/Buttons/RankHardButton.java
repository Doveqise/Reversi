package RankList.Buttons;

import RankList.PlayerList;

public class RankHardButton extends BasicButton{
    public RankHardButton(int w, int h){
        super(w, h);
        this.setText("Rank in Hard");
    }

    @Override
    public void onMouseClicked(){
        PlayerList.rank(7);
    }
}
