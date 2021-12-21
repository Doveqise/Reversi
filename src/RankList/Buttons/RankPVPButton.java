package RankList.Buttons;

import RankList.PlayerList;

public class RankPVPButton extends BasicButton{
    public RankPVPButton(int w, int h){
        super(w, h);
        this.setText("Rank in PVP");
    }

    @Override
    public void onMouseClicked(){
        PlayerList.rank(1);
    }
}
