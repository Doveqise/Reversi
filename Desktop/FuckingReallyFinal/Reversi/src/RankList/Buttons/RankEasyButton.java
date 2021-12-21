package RankList.Buttons;

import RankList.PlayerList;

public class RankEasyButton extends BasicButton{
    public RankEasyButton(int w, int h){
        super(w, h);
        this.setText("Rank in Easy");
    }

    @Override
    public void onMouseClicked(){
        PlayerList.rank(5);
    }
}
