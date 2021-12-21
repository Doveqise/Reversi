package GameFrame.StepRecorder;

public class StepList {
    public int cnt;
    public Step[] steps;

    public StepList(){
        steps= new Step[64];
        cnt= 0;
    }

    public void addStep(Step s){
        cnt++;
        steps[cnt]= s;
    }

    public String outPut(){
        String ans= "";
        for(int i= 1;i<= cnt;i++){
            Step x= steps[i];
            ans+= String.format("%d\n%d\n%d\n%s\n%d\n", x.x, x.y, x.chess, x.name, x.cheat== true? 1: 0);
        }
        return ans;
    }

    public String outPutState(){
        String ans= "";
        for(int i= 1;i<= cnt;i++){
            Step x= steps[i];
            ans+= String.format("%-15s%-8s%-8d%-8d%-8s\n", x.name, x.chess== 1? "Black":"White", x.x, x.y, x.cheat== true? "ON": "OFF");
        }
        return ans;
    }
}
