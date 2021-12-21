package GameFrame.StepRecorder;

public class Step {
    public boolean cheat;
    public String name;
    public int chess;
    public int x;
    public int y;

    public Step(int x, int y, int chess, String name, boolean cheat){
        this.x= x;
        this.y= y;
        this.chess= chess;
        this.name= name;
        this.cheat= cheat;
    }
}
