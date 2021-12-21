package RankList;

public class Player {
    public String name;
    public int[] scores;
    public int pvpW;       //1
    public int pvpL;       //2
    public int monkeyW;    //3
    public int monkeyL;    //4
    public int easyW;      //5
    public int easyL;      //6
    public int hardW;      //7
    public int hardL;      //8

    public Player(String name){
        this.name= name;
        scores= new int[10];
        for(int i= 1;i<= 8;i++){
            scores[i]= 0;
        }
    }

    public Player(String name, int pvpW, int pvpL, int monkeyW, int monkeyL, int easyW, int easyL, int hardW, int hardL){
        this.name= name;
        scores= new int[10];
        this.scores[1]= pvpW;
        this.scores[2]= pvpL;
        this.scores[3]= monkeyW;
        this.scores[4]= monkeyL;
        this.scores[5]= easyW;
        this.scores[6]= easyL;
        this.scores[7]= hardW;
        this.scores[8]= hardL;
    }
}
