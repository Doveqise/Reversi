package GameFrame.AIFoolish;

import GameFrame.ReversiFrame;
import MainMenu.GameMenu;

public abstract class AI {
    public String aiName;
    public int currentChess; //1 is black -1 is white
    public int[][] board; //from 1- 8
    public int[][] preDot; // from 1- 8 give you all possible place position

    public abstract Position judge();

    public void load(){
        ReversiFrame.controller.getBoard();
        ReversiFrame.controller.generatePreDot();
        currentChess= ReversiFrame.controller.currentChess;
        preDot= ReversiFrame.controller.preDot;
        board= ReversiFrame.controller.board;
    }

    public static int GetRandomNum(int num1, int num2)
    {
        int result = (int) (num1 + Math.random() * (num2 - num1 + 1));
        return result;
    }
}
