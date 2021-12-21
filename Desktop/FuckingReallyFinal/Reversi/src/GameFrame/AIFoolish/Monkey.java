package GameFrame.AIFoolish;

import GameFrame.ReversiFrame;
import java.util.ArrayList;

public class Monkey extends AI{
    public Monkey(){
        super();
        aiName= "Monkey";
    }

    @Override
    public Position judge(){
        load();
        ArrayList<Position> arrayList= new ArrayList<>();

        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                if(preDot[i][j]== 1){
                    arrayList.add(new Position(i, j) );
                }
            }
        }

        int size= arrayList.size();
        int ans= GetRandomNum(1, size);
        return arrayList.get(ans- 1);
    }
}
