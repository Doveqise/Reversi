package GameFrame.AIFoolish;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DeepSearch extends AI{
    public DeepSearch(){
        super();
        aiName= "Hard";
    }

    @Override
    public Position judge(){
        load();
        final int[][] valueTable =
                {
                        {0,0  ,0  ,0  ,0  ,0  ,0  ,0  ,0  },
                        {0,500,-25,10 ,5  ,5  ,10 ,-25,500},
                        {0,-25,-45,1  ,1  ,1  ,1  ,-45,-25},
                        {0,10 ,1  ,3  ,2  ,2  ,3  ,1  ,10 },
                        {0,5  ,1  ,2  ,1  ,1  ,2  ,1  ,5  },
                        {0,5  ,1  ,2  ,1  ,1  ,2  ,1  ,5  },
                        {0,10 ,1  ,3  ,2  ,2  ,3  ,1  ,10 },
                        {0,-25,-45,1  ,1  ,1  ,1  ,-45,-25},
                        {0,500,-25,10 ,5  ,5  ,10 ,-25,500}
                };
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
                    priorityQueue.add(new PositionWithValue(i, j, valueTable[i][j]) );
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
