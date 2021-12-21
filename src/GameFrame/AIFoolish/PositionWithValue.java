package GameFrame.AIFoolish;

public class PositionWithValue extends Position{
    public int val;
    public PositionWithValue(int x, int y,int val)
    {
        super(x,y);
        this.val = val;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}
