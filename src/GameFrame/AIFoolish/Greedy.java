package GameFrame.AIFoolish;

import GameFrame.GameController.BasicController;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Greedy extends AI{

    public int CountChange(int[][] a, int[][] mode){
        int ans = 0;

        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                if(a[i][j]!= mode[i][j]){
                    ans++;
                    a[i][j]= mode[i][j];
                }
            }
        }
        return ans;
    }
    @Override
    public Position judge(){
        load();
        int[][] tmp = new int[10][10];
        PriorityQueue<PositionWithValue> priorityQueue= new PriorityQueue<PositionWithValue>(100, new Comparator<PositionWithValue>() {
            @Override
            public int compare(PositionWithValue o1, PositionWithValue o2)
            {
                return (o1.val == o2.val)?
                            (1)
                            :
                            (o1.val-o2.val);
            }
        });

        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                if(preDot[i][j] == 1){
                    BasicController.notIdentify(tmp,board);
                    BasicController.placingChess(tmp, i, j, currentChess);
                    int reverseVal = CountChange(tmp,board);
                    priorityQueue.add(new PositionWithValue(i, j, reverseVal) );
                }
            }
        }

        PositionWithValue ret = priorityQueue.poll();
        if(ret.val<= -100){
            return null;
        }
        return new Position(ret.getX(), ret.getY());
    }
}
